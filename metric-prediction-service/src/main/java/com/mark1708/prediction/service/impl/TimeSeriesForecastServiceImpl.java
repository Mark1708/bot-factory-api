package com.mark1708.prediction.service.impl;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.max;
import static org.apache.spark.sql.functions.min;
import static org.apache.spark.sql.functions.round;

import com.mark1708.clients.prediction.dto.PredictedItem;
import com.mark1708.prediction.exception.PredictionException;
import com.mark1708.prediction.service.TimeSeriesForecastService;
import com.mark1708.prediction.util.FeatureGenerator;
import com.mark1708.prediction.util.PredictionDataGenerator;
import com.mark1708.prediction.util.TimeSeriesSplit;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.feature.VectorAssembler;
import org.apache.spark.ml.regression.DecisionTreeRegressor;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TimeSeriesForecastServiceImpl implements TimeSeriesForecastService {


  private String targetName;
  private String dateTimeName;

  private final SparkSession sparkSession;

  @Override
  public List<PredictedItem> predict(Dataset<Row> originalData, String orderId, Integer days) {
    try {
      String[] originalColumns = originalData.columns();
      dateTimeName = originalColumns[0];
      targetName = originalColumns[1];

      Dataset<Row> resultData = originalData;
      Column dateTime = col(dateTimeName);

      // Инициализируем шенератор данных
      PredictionDataGenerator predictionDataGenerator =
          new PredictionDataGenerator(sparkSession, dateTimeName, targetName);
      // Определяем дату начала и дату конца данных для предсказания
      LocalDateTime fromDate = resultData.agg(min(dateTime), max(dateTime)).first()
          .getTimestamp(1)
          .toLocalDateTime()
          .plusDays(1);
      LocalDateTime toDate = fromDate.plusDays(1);

      // Создаём список шагов для динамических признаков
      List<Integer> lags = List.of(1, 3, 7, 14);
      // Генерируем названия признаков
      List<String> featureColumns = FeatureGenerator.getFeatures(lags);

      // Инициализируем Pipeline
      Pipeline pipeline = getPipeline(targetName, featureColumns.toArray(String[]::new));

      for (int i = 0; i < days; i++) {
        log.info("[{}] Start forecasting of day {} [from {} to {}]", orderId, i + 1, fromDate,
            toDate);
        // Генерируем данные для предсказания
        Dataset<Row> dateTimeRange = predictionDataGenerator.generate(fromDate, toDate);

        // Смещаем даты для слеующей итерации
        fromDate = toDate;
        toDate = toDate.plusDays(1);
        // Соединяем с исходными
        Dataset<Row> fullData = resultData.unionByName(dateTimeRange);

        // Генерируем признаки
        FeatureGenerator featureGenerator = new FeatureGenerator(lags, fullData);
        Dataset<Row> fullDataWithFeatures = featureGenerator.generate().orderBy(dateTime.asc());

        TimeSeriesSplit timeSeriesSplit = new TimeSeriesSplit(fullDataWithFeatures);
        // Выделяем данные для обучения
        Dataset<Row> train = timeSeriesSplit.getTrainDataset();
        // Обучаем модель
        PipelineModel model = pipeline.fit(train);

        // Выделяем данные для предсказания
        Dataset<Row> eval = timeSeriesSplit.getEvalDataset();
        // Предсказываем данные
        Dataset<Row> predicted = model.transform(eval);

        // Добавляем предсказанные значения в resultData, который будем использовать
        // для обучения на следующей итерации
        Dataset<Row> prediction = predicted.select(dateTimeName, "prediction")
            .withColumn(targetName, round(col("prediction")))
            .drop("prediction");
        resultData = resultData.unionByName(prediction);
      }
      resultData = resultData.orderBy(dateTime.desc()).limit(days).orderBy(dateTime.asc());
      return resultData
          .collectAsList()
          .stream()
          .map(
              row -> new PredictedItem(
                  row.getTimestamp(0).toLocalDateTime(),
                  row.getDouble(1)
              )
          ).collect(Collectors.toList());
    } catch (Exception e) {
      throw new PredictionException(
          String.format("Error during prediction of order [%s]:\n%s", orderId, e.getMessage())
      );
    }
  }

  private Pipeline getPipeline(String labelName, String[] featureColumns) {
    // Преобразователь (Transformer), который принимает данные и возвращает преобразованные, с добавлением нового
    // столбца который является векторным представлением всех фичей (функций)
    VectorAssembler assembler = new VectorAssembler()
        .setInputCols(featureColumns)
        .setOutputCol("features")
        .setHandleInvalid("keep");

    DecisionTreeRegressor regression = new DecisionTreeRegressor()
        .setLabelCol(labelName)
        .setFeaturesCol("features")
        .setMaxBins(100)
        .setMaxDepth(2);
    // mae evaluation: 2.5676108374384237
    // rmse evaluation: 3.2354788992429446

    // Конвеер (Pipeline), который связывает преобразователи и оценщики в единый процесс
    return new Pipeline()
        .setStages(new PipelineStage[]{assembler, regression});
  }
}
