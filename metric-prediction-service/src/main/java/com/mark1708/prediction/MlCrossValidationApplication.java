//package com.mark1708.prediction;
//
//import static org.apache.spark.sql.functions.col;
//import static org.apache.spark.sql.functions.lag;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.spark.ml.Pipeline;
//import org.apache.spark.ml.PipelineStage;
//import org.apache.spark.ml.evaluation.RegressionEvaluator;
//import org.apache.spark.ml.feature.Normalizer;
//import org.apache.spark.ml.feature.VectorAssembler;
//import org.apache.spark.ml.param.ParamMap;
//import org.apache.spark.ml.regression.DecisionTreeRegressor;
//import org.apache.spark.ml.tuning.CrossValidator;
//import org.apache.spark.ml.tuning.CrossValidatorModel;
//import org.apache.spark.ml.tuning.ParamGridBuilder;
//import org.apache.spark.sql.Dataset;
//import org.apache.spark.sql.Row;
//import org.apache.spark.sql.SparkSession;
//import org.apache.spark.sql.expressions.Window;
//import org.apache.spark.sql.expressions.WindowSpec;
//import org.apache.spark.sql.functions;
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
//public class MlCrossValidationApplication {
//
//  private final SparkSession sparkSession;
//
//  public static void main(String[] args) {
//    SpringApplication.run(MlCrossValidationApplication.class, args);
//  }
//
//  @Bean
//  public CommandLineRunner CommandLineRunnerBean() {
//    return (args) -> {
////      System.out.println("Success");
//      Dataset<Row> originalData = sparkSession.read().format("csv")
//          .option("sep", ",")
//          .option("inferSchema", "true")
//          .option("header", "true")
//          .load("src/main/resources/user_activities.csv");
//
//      WindowSpec window = Window.orderBy(col("date_time").asc());
//
//      Dataset<Row> dataWithFeatures = originalData
//          .orderBy(col("date_time"))
//          .withColumn("hour", functions.hour(col("date_time")))
//          .withColumn("dayofweek", functions.dayofweek(col("date_time")))
//          .withColumn("quarter", functions.quarter(col("date_time")))
//          .withColumn("month", functions.month(col("date_time")))
//          .withColumn("year", functions.year(col("date_time")))
//          .withColumn("dayofyear", functions.dayofyear(col("date_time")))
//          .withColumn("dayofmonth", functions.dayofmonth(col("date_time")))
//          .withColumn("weekofyear", functions.weekofyear(col("date_time")))
//          .withColumn("lag_7days", lag(col("online_clients"), 7, 0).over(window))
//          .withColumn("lag_14days", lag(col("online_clients"), 14, 0).over(window))
//          .withColumn("lag_30days", lag(col("online_clients"), 30, 0).over(window))
//          .persist(StorageLevel.MEMORY_AND_DISK())
//          .repartition(100, col("date_time"));
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
////      LogisticRegression regression = new LogisticRegression()
////          .setLabelCol(labelName)
////          .setFeaturesCol("features")
////          .setElasticNetParam(0.2)
////          .setMaxIter(10)
////          .setRegParam(0.001);
//      // mae evaluation: 2.7777777777777777
//      // rmse evaluation: 5.109903238918631
//
////      RandomForestRegressor regression = new RandomForestRegressor()
////          .setLabelCol(labelName)
////          .setFeaturesCol("features")
////          .setMaxBins(100)
////          .setMaxDepth(2)
////          .setNumTrees(5);
//      // mae evaluation: 2.8762214822390364
//      // rmse evaluation: 4.624328537643242
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
//      // Параметры для преобразователей и оценщиков
//      ParamMap[] paramGrid = new ParamGridBuilder()
////          .addGrid(regression.maxIter(), new int[]{10, 100})
////          .addGrid(regression.regParam(), new double[]{0.001, 0.02})
////          .addGrid(regression.elasticNetParam(), new double[]{0.2, 0.8})
//          .addGrid(regression.maxDepth(), new int[]{2, 7, 10})
//          .addGrid(regression.maxBins(), new int[]{100, 200})
//          .build();
//
//      double testFraction = 0.2;
//      Dataset<Row>[] splitsFold = dataWithFeatures.randomSplit(
//            new double[]{1 - testFraction, testFraction});
//
//        // Получаем тренировочные и тестовые данные
//        Dataset<Row> trainData = splitsFold[0];
//        Dataset<Row> testData = splitsFold[1];
//
//      // Оценка качества
//      RegressionEvaluator evaluator = new RegressionEvaluator()
//          .setLabelCol(labelName)
//          .setPredictionCol("prediction")
//          .setMetricName("rmse");
//
//      CrossValidator crossvalidator = new CrossValidator()
//          .setEstimator(pipeline)
//          .setEvaluator(evaluator)
//          .setEstimatorParamMaps(paramGrid)
//          .setNumFolds(3);
//
//      // Передаем данные в pipeline
//      CrossValidatorModel pipelineModel = crossvalidator.fit(trainData);
//
//      ParamMap[] bestEstimatorParamMap = pipelineModel.getEstimatorParamMaps();
//
//      log.info("best params map {}", bestEstimatorParamMap);
//
//      // Преобразуем данные
//      Dataset<Row> predictions = pipelineModel.transform(testData);
//      Dataset<Row> result = predictions.withColumn("error", col("prediction").minus(col(labelName)));
//      result.select(labelName, "prediction", "error").show();
//      result.describe(labelName, "prediction", "error").show();
//
//      RegressionEvaluator maevaluator = new RegressionEvaluator()
//          .setLabelCol(labelName)
//          .setMetricName("mae");
//      log.info("mae evaluation: {}", maevaluator.evaluate(predictions));
//
//      RegressionEvaluator rmsevaluator = new RegressionEvaluator()
//          .setLabelCol(labelName)
//          .setMetricName("rmse");
//      log.info("rmse evaluation: {}", rmsevaluator.evaluate(predictions));
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
