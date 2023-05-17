//package com.mark1708.prediction;
//
//import static org.apache.spark.sql.functions.col;
//import static org.apache.spark.sql.functions.lag;
//import static org.apache.spark.sql.functions.max;
//import static org.apache.spark.sql.functions.min;
//
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.time.temporal.ChronoUnit;
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
//public class PipelineApplication {
//
//  private final SparkSession sparkSession;
//
//  public static void main(String[] args) {
//    SpringApplication.run(PipelineApplication.class, args);
//  }
//
//  @Bean
//  public CommandLineRunner CommandLineRunnerBean() {
//    return (args) -> {
////      System.out.println("Success");
//      StructType schema = new StructType()
//          .add("date_time", "timestamp")
//          .add("online_clients", "long");
//
//      Dataset<Row> originalData = sparkSession.read().format("csv")
//          .option("sep", ",")
//          .option("inferSchema", "true")
//          .option("header", "true")
//          .schema(schema)
//          .load("src/main/resources/user_activities.csv");
//
//      Dataset<Row> dataWithFeatures = createFeatures(originalData);
//
//      String labelName = "online_clients";
//      String[] featureColumns = {"hour", "dayofweek", "quarter", "month", "year",
//          "dayofyear", "dayofmonth", "weekofyear", "lag_7days", "lag_14days", "lag_30days"};
//
//
//      // Преобразователь (Transformer), который принимает данные и возвращает преобразованные, с добавлением нового
//      // столбца который является векторным представлением всех фичей (функций)
//      VectorAssembler assembler = new VectorAssembler()
//          .setInputCols(featureColumns)
//          .setOutputCol("rawfeatures");
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
//      // mae evaluation: 2.5676108374384237
//      // rmse evaluation: 3.2354788992429446
//
//      // Конвеер (Pipeline), который связывает преобразователи и оценщики в единый процесс
//      Pipeline pipeline = new Pipeline()
//          .setStages(new PipelineStage[]{assembler, normalizer, regression});
//
//      log.info("Train data");
//      dataWithFeatures.show();
//      PipelineModel model = pipeline.fit(dataWithFeatures);
//
//      Column dateTime = dataWithFeatures.col("date_time");
//
//      LocalDateTime fromDate = dataWithFeatures.agg(min(dateTime), max(dateTime)).first()
//          .getTimestamp(1).toLocalDateTime();
//      LocalDateTime toDate = LocalDateTime.of(2023, 8, 17, 0, 0);
//
//      Dataset<Row> dateTimeRange = getDateTimeRange(sparkSession, fromDate.plusDays(1), toDate)
//          .withColumn("online_clients", functions.lit(null));
//      Dataset<Row> dataForPredict = createFeatures(dateTimeRange);
//
//      Dataset<Row> predicted = model.transform(dataForPredict);
//      log.info("Predicted data");
//      predicted.show();
//
////      Dataset<Row> join = dataWithFeatures.unionByName(dataForPredict);
//
//
//
////      Duration between = Duration.between(
////          fromDate,
////          toDate
////      );
////
////      List<LocalDateTime> range = new ArrayList<>();
////      for (LocalDateTime start = fromDate; start.isBefore(toDate) ; start.plusDays(1))
//
////      // Определяем количество разбиений
////      long dataSize = dataWithFeatures.count(); // получаем размер данных
////      int numPartitions = getNumPartitions(dataSize); // количество частей
////
////      double[] fractions = new double[numPartitions];
////      Arrays.fill(fractions, 1.0 / numPartitions);
////
////      Dataset<Row>[] splits = dataWithFeatures.randomSplit(fractions);
////
////      // Определяем процент записей, которые будут использоваться в качестве тестовых данных
////      double testFraction = 0.2;
////
//
//
////      for (int i = 0; i < numPartitions; i++) {
////        // Разделяем данные на тренировочные и тестовые
////        Dataset<Row>[] splitsFold = splits[i].randomSplit(
////            new double[]{1 - testFraction, testFraction});
////
////        // Получаем тренировочные и тестовые данные
////        Dataset<Row> trainData = splitsFold[0];
////        Dataset<Row> testData = splitsFold[1];
////
////
////
////        System.out.println("Fold: " + splits[i].count() + "\tTrain: " + trainData.count() + "\tTest: " + testData.count());
////      }
////      dataWithFeatures.show();
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
//  private static Dataset<Row> createFeatures(Dataset<Row> originalData) {
//    WindowSpec window = Window.orderBy(col("date_time").asc());
//
//    return originalData
//        .orderBy(col("date_time"))
//        .withColumn("hour", functions.hour(col("date_time")))
//        .withColumn("dayofweek", functions.dayofweek(col("date_time")))
//        .withColumn("quarter", functions.quarter(col("date_time")))
//        .withColumn("month", functions.month(col("date_time")))
//        .withColumn("year", functions.year(col("date_time")))
//        .withColumn("dayofyear", functions.dayofyear(col("date_time")))
//        .withColumn("dayofmonth", functions.dayofmonth(col("date_time")))
//        .withColumn("weekofyear", functions.weekofyear(col("date_time")))
//        .withColumn("lag_7days", lag(col("online_clients"), 7, null).over(window))
//        .withColumn("lag_14days", lag(col("online_clients"), 14, null).over(window))
//        .withColumn("lag_30days", lag(col("online_clients"), 30, null).over(window))
//        .withColumn("isFuture", functions.isnull(col("online_clients")))
//        .persist(StorageLevel.MEMORY_AND_DISK())
//        .repartition(100, col("date_time"));
//  }
//
//  private static int getNumPartitions(long dataSize) {
//    if (dataSize < 1000000) {
//      return 3; // маленький датасет - разбиваем на 3 части
//    } else if (dataSize < 10000000) {
//      return 5; // средний датасет - разбиваем на 5 частей
//    } else {
//      return 10; // большой датасет - разбиваем на 10 частей
//    }
//  }
//
//}
