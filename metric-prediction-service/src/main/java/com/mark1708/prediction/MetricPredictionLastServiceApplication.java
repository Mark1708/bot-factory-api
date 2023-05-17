//package com.mark1708.prediction;
//
//import static org.apache.spark.sql.functions.call_udf;
//import static org.apache.spark.sql.functions.col;
//import static org.apache.spark.sql.functions.dayofmonth;
//import static org.apache.spark.sql.functions.dayofweek;
//import static org.apache.spark.sql.functions.dayofyear;
//import static org.apache.spark.sql.functions.hour;
//import static org.apache.spark.sql.functions.isnull;
//import static org.apache.spark.sql.functions.lag;
//import static org.apache.spark.sql.functions.max;
//import static org.apache.spark.sql.functions.mean;
//import static org.apache.spark.sql.functions.min;
//import static org.apache.spark.sql.functions.month;
//import static org.apache.spark.sql.functions.quarter;
//import static org.apache.spark.sql.functions.weekofyear;
//import static org.apache.spark.sql.functions.year;
//
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.time.temporal.ChronoUnit;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.spark.ml.Pipeline;
//import org.apache.spark.ml.PipelineModel;
//import org.apache.spark.ml.PipelineStage;
//import org.apache.spark.ml.feature.Normalizer;
//import org.apache.spark.ml.feature.VectorAssembler;
//import org.apache.spark.ml.regression.DecisionTreeRegressor;
//import org.apache.spark.sql.Column;
//import org.apache.spark.sql.Dataset;
//import org.apache.spark.sql.Row;
//import org.apache.spark.sql.RowFactory;
//import org.apache.spark.sql.SparkSession;
//import org.apache.spark.sql.expressions.Window;
//import org.apache.spark.sql.expressions.WindowSpec;
//import org.apache.spark.sql.functions;
//import org.apache.spark.sql.types.StructType;
//import org.apache.spark.storage.StorageLevel;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//
//
//@Slf4j
//@SpringBootApplication
//@RequiredArgsConstructor
//public class MetricPredictionLastServiceApplication {
//
//  private final SparkSession sparkSession;
//
//  public static void main(String[] args) {
//    SpringApplication.run(MetricPredictionLastServiceApplication.class, args);
//  }
//
//  @Bean
//  public CommandLineRunner CommandLineRunnerBean() {
//    return (args) -> {
////      System.out.println("Success");
//
//
//      String labelName = "online_clients";
////      String labelName = "PJME_MW";
//      String[] featureColumns = {"hour", "month", "year", "quarter", "day_of_week", "day_of_year",
//          "day_of_month", "week_of_year", "is_weekend", "day_in_month"
//          , "min_value_per_month",
//          "max_value_per_month", "mean_value_per_month", "min_value_per_year", "max_value_per_year",
//          "mean_value_per_year"
//          , "lag_7days", "lag_14days", "lag_30days"
//      };
//
//      // Преобразователь (Transformer), который принимает данные и возвращает преобразованные, с добавлением нового
//      // столбца который является векторным представлением всех фичей (функций)
//      VectorAssembler assembler = new VectorAssembler()
//          .setInputCols(featureColumns)
//          .setOutputCol("rawfeatures")
//          .setHandleInvalid("skip");
//
//      // Преобразователь (Transformer), который все числовые данные переводит в диапазон между 0 и 1
//      // StandardScaler -> StandardScalerModel
//      Normalizer normalizer = new Normalizer()
//          .setInputCol("rawfeatures")
//          .setOutputCol("features")
//          // p=1 - Манхэттенское расстояние
//          // p=2 - Евклидово расстояние
//          .setP(1.0);
//
//
//      DecisionTreeRegressor regression = new DecisionTreeRegressor()
//          .setLabelCol(labelName)
//          .setFeaturesCol("features")
//          .setMaxBins(100)
//          .setMaxDepth(2);
////      // mae evaluation: 2.5676108374384237
////      // rmse evaluation: 3.2354788992429446
//
////            LogisticRegression regression = new LogisticRegression()
////          .setLabelCol(labelName)
////          .setFeaturesCol("features")
////          .setElasticNetParam(0.2)
////          .setMaxIter(10)
////          .setRegParam(0.001);
//      // mae evaluation: 2.7777777777777777
//      // rmse evaluation: 5.109903238918631
//
//      // Конвеер (Pipeline), который связывает преобразователи и оценщики в единый процесс
//      Pipeline pipeline = new Pipeline()
//          .setStages(new PipelineStage[]{assembler, normalizer, regression});
//
//      StructType schema = new StructType()
//          .add("date_time", "timestamp")
//          .add(labelName, "double");
//
//      Dataset<Row> originalData = sparkSession.read().format("csv")
//          .option("sep", ",")
//          .option("inferSchema", "true")
//          .option("header", "true")
//          .schema(schema)
////          .load("src/main/resources/PJME_hourly.csv");
//          .load("src/main/resources/user_activities.csv");
//
//      Column dateTime = originalData.col("date_time");
//
//      LocalDateTime fromDate = originalData.agg(min(dateTime), max(dateTime)).first()
//          .getTimestamp(1).toLocalDateTime();
//      LocalDateTime toDate = fromDate.plusMonths(2);
//
//      Dataset<Row> dateTimeRange = getDateTimeRange(sparkSession, fromDate.plusDays(1), toDate)
//          .withColumn(labelName, functions.lit(null));
//
//      Dataset<Row> fullDataWithFeatures = createFeatures(originalData.unionByName(dateTimeRange), labelName);
//
//      Dataset<Row> train = fullDataWithFeatures
//          .filter(col("is_future").equalTo(false))
//          .orderBy(col("date_time"));
//      log.info("Train data\tSize: {}", train.count());
//      train.show(5);
////      train.printSchema();
//
//      Dataset<Row> eval = fullDataWithFeatures
//          .filter(col("is_future").equalTo(true))
//          .orderBy(col("date_time"));
//      log.info("Eval data\tSize: {}", eval.count());
//      eval.show(5);
////      eval.printSchema();
//
//      log.info("Start train model");
//      PipelineModel model = pipeline.fit(train);
//      log.info("Finish train model");
//
//
//      log.info("Start predicting");
//      Dataset<Row> predicted = model.transform(eval)
//          .orderBy(col("date_time"));
//      log.info("Finish predicting");
//
//      log.info("Predicted data\tSize: {}", predicted.count());
//      predicted.show(10);
//
//
//      Dataset<Row> dataForExport = predicted.select("date_time", "prediction");
//
////      dataForExport
////          .write()
////          .format("csv")
////          .option("header", "true")
////          .option("delimiter", ",")
////          .option("encoding", "UTF-8")
////          .save("src/main/resources/result.csv");
//
//      model.write().overwrite().save("src/main/resources/models/decision-tree-regressor");
//
//      log.info(Arrays.toString(dataForExport
//          .collectAsList()
//          .stream()
//          .map(row -> new PredictedItem(
//              row.getTimestamp(0).toLocalDateTime(),
//              row.getDouble(1))
//          ).toArray()));
//    };
//  }
//
//  private Dataset<Row> getDateTimeRange(SparkSession sparkSession, LocalDateTime fromDate, LocalDateTime toDate) {
//    long numOfDaysBetween = ChronoUnit.DAYS.between(fromDate, toDate);
//    List<Row> dates = IntStream.iterate(0, i -> i + 1)
//        .limit(numOfDaysBetween)
//        .mapToObj(fromDate::plusDays)
//        .map(Timestamp::valueOf)
//        .map(RowFactory::create)
//        .collect(Collectors.toList());
//    StructType schema = new StructType()
//        .add("date_time", "timestamp");
//    return sparkSession.createDataFrame(dates, schema);
//  }
//
//  private static Dataset<Row> createFeatures(Dataset<Row> originalData, String target) {
//    Column dateTime = col("date_time");
//    WindowSpec window1 = Window.orderBy(dateTime.asc());
//    Column onlineClientsDesc = col(target).desc();
//    WindowSpec window2 = Window.partitionBy(month(dateTime), year(dateTime))
//        .orderBy(onlineClientsDesc);
//    WindowSpec window3 = Window.partitionBy(year(dateTime))
//        .orderBy(onlineClientsDesc);
//
//    return originalData
//        .orderBy(col("date_time"))
//        .withColumn("hour", hour(col("date_time")))
//        .withColumn("month", month(col("date_time")))
//        .withColumn("year", year(col("date_time")))
//        .withColumn("quarter", quarter(col("date_time")))
//        .withColumn("day_of_week", dayofweek(col("date_time")))
//        .withColumn("day_of_year", dayofyear(col("date_time")))
//        .withColumn("day_of_month", dayofmonth(col("date_time")))
//        .withColumn("week_of_year", weekofyear(col("date_time")))
//        .withColumn("is_weekend", call_udf("isWeekend", col("date_time")))
//        .withColumn("day_in_month", call_udf("dayInMonth", col("date_time")))
//        .withColumn("min_value_per_month", min(target).over(window2))
//        .withColumn("max_value_per_month", max(target).over(window2))
//        .withColumn("mean_value_per_month", mean(target).over(window2))
//        .withColumn("min_value_per_year", min(target).over(window3))
//        .withColumn("max_value_per_year", max(target).over(window3))
//        .withColumn("mean_value_per_year", mean(target).over(window3))
//        .withColumn("lag_7days", lag(col(target), 7).over(window1))
//        .withColumn("lag_14days", lag(col(target), 14).over(window1))
//        .withColumn("lag_30days", lag(col(target), 30).over(window1))
//        .withColumn("is_future", isnull(col(target)))
//        .persist(StorageLevel.MEMORY_AND_DISK())
//        .repartition(100, col("date_time"));
//  }
//
//}
