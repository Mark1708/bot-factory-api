package com.mark1708.prediction.service;

import static org.apache.spark.sql.functions.avg;
import static org.apache.spark.sql.functions.call_udf;
import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.dayofmonth;
import static org.apache.spark.sql.functions.dayofweek;
import static org.apache.spark.sql.functions.dayofyear;
import static org.apache.spark.sql.functions.hour;
import static org.apache.spark.sql.functions.isnull;
import static org.apache.spark.sql.functions.lag;
import static org.apache.spark.sql.functions.lit;
import static org.apache.spark.sql.functions.max;
import static org.apache.spark.sql.functions.mean;
import static org.apache.spark.sql.functions.min;
import static org.apache.spark.sql.functions.month;
import static org.apache.spark.sql.functions.quarter;
import static org.apache.spark.sql.functions.round;
import static org.apache.spark.sql.functions.weekofyear;
import static org.apache.spark.sql.functions.when;
import static org.apache.spark.sql.functions.year;

import com.mark1708.prediction.PredictedItem;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.expressions.Window;
import org.apache.spark.sql.expressions.WindowSpec;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.storage.StorageLevel;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PredictionService {


  private String targetName;
  private String dateTimeName;

  private final SparkSession sparkSession;

  public List<PredictedItem> predict(Dataset<Row> originalData, int days) {

    String[] originalColumns = originalData.columns();
    dateTimeName = originalColumns[0];
    targetName = originalColumns[1];

    Column dateTime = originalData.col(dateTimeName);
    List<String> featureColumns = new ArrayList<>(
        List.of(
            "hour", "month", "year", "quarter", "day_of_week",
            "day_of_year", "day_of_month", "week_of_year", "is_weekend", "day_in_month",
            "min_value_per_month", "max_value_per_month", "mean_value_per_month",
            "min_value_per_year", "max_value_per_year", "mean_value_per_year",
            "lag_1days", "lag_3days", "lag_7days", "lag_14days",
            "lag_30days",
            "moving_avg_1days", "moving_avg_3days", "moving_avg_7days", "moving_avg_14days",
            "moving_avg_30days",
            "trend_1days", "trend_3days", "trend_7days", "trend_14days",
            "trend_30days"
        ));

    Pipeline pipeline = getNewPipeline(targetName, featureColumns.toArray(String[]::new));

    Dataset<Row> resultData = originalData;
    for (int i = 0; i < days; i++) {
      // Определяем дату начала и дату конца данных для предсказания
      LocalDateTime fromDate = resultData.agg(min(dateTime), max(dateTime)).first()
          .getTimestamp(1)
          .toLocalDateTime()
          .plusDays(1);
      LocalDateTime toDate = fromDate.plusDays(1);

      // Генерируем данные для предсказания
      Dataset<Row> dateTimeRange = getDateTimeRange(sparkSession, fromDate, toDate)
          .withColumn(targetName, functions.lit(null));
//      dateTimeRange.show();
      // Соединяем с исходными
      Dataset<Row> fullData = resultData.unionByName(dateTimeRange);

      // Генерируем признаки
      Dataset<Row> fullDataWithFeatures = createExtendedFeatures(fullData, targetName)
          .persist(StorageLevel.MEMORY_AND_DISK())
          .repartition(100, dateTime);

      // Выделяем данные для обучения
      Dataset<Row> train = fullDataWithFeatures
          .filter(col("is_future").equalTo(false))
          .orderBy(dateTime).persist(StorageLevel.MEMORY_AND_DISK());
      log.info("Train data\tSize: {}", train.count());
//      train.show(5);

      // Выделяем данные для предсказания
      Dataset<Row> eval = fullDataWithFeatures
          .filter(col("is_future").equalTo(true))
          .orderBy(dateTime).persist(StorageLevel.MEMORY_AND_DISK());
      log.info("Eval data\tSize: {}", eval.count());
//      eval.show();

      log.info("Start train model");
      PipelineModel model = pipeline.fit(train);
//      log.info("Finish train model");

      log.info("Start predicting");
      Dataset<Row> predicted = model.transform(eval)
          .orderBy(col(dateTimeName));
//      log.info("Finish predicting");

      log.info("Predicted data\tSize: {}", predicted.count());
      Dataset<Row> prediction = predicted.select(dateTimeName, "prediction")
          .withColumn(targetName , round(col("prediction")))
          .drop("prediction");
      resultData = resultData.unionByName(prediction);
    }
    resultData = resultData.orderBy(dateTime.desc()).limit(days);
    resultData.show();
    return List.of();
  }


  private Pipeline getNewPipeline(String labelName, String[] featureColumns) {
    // Преобразователь (Transformer), который принимает данные и возвращает преобразованные, с добавлением нового
    // столбца который является векторным представлением всех фичей (функций)
    VectorAssembler assembler = new VectorAssembler()
        .setInputCols(featureColumns)
        .setOutputCol("rawfeatures")
        .setHandleInvalid("skip");

//    // Преобразователь (Transformer), который все числовые данные переводит в диапазон между 0 и 1
//    // StandardScaler -> StandardScalerModel
//    Normalizer normalizer = new Normalizer()
//        .setInputCol("rawfeatures")
//        .setOutputCol("features")
//        // p=1 - Манхэттенское расстояние
//        // p=2 - Евклидово расстояние
//        .setP(1.0);

    DecisionTreeRegressor regression = new DecisionTreeRegressor()
        .setLabelCol(labelName)
//        .setFeaturesCol("features")
        .setFeaturesCol("rawfeatures")
//        .setMaxBins(100)
        .setMaxDepth(2);
    // mae evaluation: 2.5676108374384237
    // rmse evaluation: 3.2354788992429446

    // Конвеер (Pipeline), который связывает преобразователи и оценщики в единый процесс
    return new Pipeline()
//        .setStages(new PipelineStage[]{assembler, normalizer, regression});
        .setStages(new PipelineStage[]{assembler, regression});
  }

  private Dataset<Row> getDateTimeRange(SparkSession sparkSession, LocalDateTime fromDate,
      LocalDateTime toDate) {
    long numOfDaysBetween = ChronoUnit.DAYS.between(fromDate, toDate);
    List<Row> dates = IntStream.iterate(0, i -> i + 1)
        .limit(numOfDaysBetween)
        .mapToObj(fromDate::plusDays)
        .map(Timestamp::valueOf)
        .map(RowFactory::create)
        .collect(Collectors.toList());

    StructType schema = new StructType()
        .add(dateTimeName, "timestamp");

    return sparkSession.createDataFrame(dates, schema);
  }

  private Dataset<Row> createFeatures(Dataset<Row> originalData, String target) {
    Column dateTime = col(dateTimeName);

    return originalData
        .orderBy(col(dateTimeName))
        .withColumn("hour", hour(dateTime))
        .withColumn("month", month(dateTime))
        .withColumn("year", year(dateTime))
        .withColumn("quarter", quarter(dateTime))
        .withColumn("day_of_week", dayofweek(dateTime))
        .withColumn("day_of_year", dayofyear(dateTime))
        .withColumn("day_of_month", dayofmonth(dateTime))
        .withColumn("week_of_year", weekofyear(dateTime))
        .withColumn("is_weekend", call_udf("isWeekend", dateTime))
        .withColumn("day_in_month", call_udf("dayInMonth", dateTime))
        .withColumn("is_future", isnull(col(target)));
  }

  private Dataset<Row> createExtendedFeatures(Dataset<Row> originalData, String target) {
    Column dateTime = col(dateTimeName);
    WindowSpec window1 = Window.orderBy(dateTime.asc());
    Column onlineClientsDesc = col(target).desc();
    WindowSpec window2 = Window.partitionBy(month(dateTime), year(dateTime))
        .orderBy(onlineClientsDesc);
    WindowSpec window3 = Window.partitionBy(year(dateTime))
        .orderBy(onlineClientsDesc);

    return createFeatures(originalData, target)
        .orderBy(dateTime)
        .withColumn("min_value_per_month", min(target).over(window2))
        .withColumn("max_value_per_month", max(target).over(window2))
        .withColumn("mean_value_per_month", mean(target).over(window2))
        .withColumn("min_value_per_year", min(target).over(window3))
        .withColumn("max_value_per_year", max(target).over(window3))
        .withColumn("mean_value_per_year", mean(target).over(window3))
        .withColumn("lag_1days", lag(col(target), 1).over(window1))
        .withColumn("lag_3days", lag(col(target), 1).over(window1))
        .withColumn("lag_7days", lag(col(target), 7).over(window1))
        .withColumn("lag_14days", lag(col(target), 14).over(window1))
        .withColumn("lag_30days", lag(col(target), 30).over(window1))
        .withColumn("moving_avg_1days", avg(col(target)).over(Window.rowsBetween(-1, 0)))
        .withColumn("moving_avg_3days", avg(col(target)).over(Window.rowsBetween(-3, 0)))
        .withColumn("moving_avg_7days", avg(col(target)).over(Window.rowsBetween(-7, 0)))
        .withColumn("moving_avg_14days", avg(col(target)).over(Window.rowsBetween(-14, 0)))
        .withColumn("moving_avg_30days", avg(col(target)).over(Window.rowsBetween(-30, 0)))
        .withColumn("trend_1days", when(col("lag_1days").isNull(), lit(null)).otherwise(col(target).minus(col("lag_1days"))))
        .withColumn("trend_3days", when(col("lag_3days").isNull(), lit(null)).otherwise(col(target).minus(col("lag_3days"))))
        .withColumn("trend_7days", when(col("lag_7days").isNull(), lit(null)).otherwise(col(target).minus(col("lag_7days"))))
        .withColumn("trend_14days", when(col("lag_14days").isNull(), lit(null)).otherwise(col(target).minus(col("lag_14days"))))
        .withColumn("trend_30days", when(col("lag_30days").isNull(), lit(null)).otherwise(col(target).minus(col("lag_30days"))))
        ;
  }
}
