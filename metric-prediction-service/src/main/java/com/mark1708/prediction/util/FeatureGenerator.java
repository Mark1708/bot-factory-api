package com.mark1708.prediction.util;

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
import static org.apache.spark.sql.functions.weekofyear;
import static org.apache.spark.sql.functions.when;
import static org.apache.spark.sql.functions.year;

import java.util.List;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.expressions.Window;
import org.apache.spark.sql.expressions.WindowSpec;
import org.apache.spark.storage.StorageLevel;

public class FeatureGenerator {

  private List<Integer> lags;
  private Dataset<Row> inputData;
  private String targetName;
  private String dateTimeName;

  private static final String lagColTemplate = "lag_%d_days";
  private static final String trendColTemplate = "trend_%d_days";
  private static final String movingAvgColTemplate = "moving_avg_%d_days";

  public FeatureGenerator(Dataset<Row> inputData) {
    this(List.of(1), inputData);
  }

  public FeatureGenerator(List<Integer> lags, Dataset<Row> inputData) {
    this.lags = lags;
    this.inputData = inputData;
    String[] columns = inputData.columns();
    this.dateTimeName = columns[0];
    this.targetName = columns[1];
  }

  public Dataset<Row> generate() {
    Column dateTime = col(dateTimeName);
    Column target = col(targetName);

    // Группировка по месяцу и году
    WindowSpec monthDataWindow = Window.partitionBy(month(dateTime), year(dateTime))
        .orderBy(target.desc());
    // Группировка по году
    WindowSpec yearDataWindow = Window.partitionBy(year(dateTime))
        .orderBy(target.desc());
    // Сортировка в порядке возрастания
    WindowSpec ascOrderedWindow = Window.orderBy(dateTime.asc());

    // Добавляем основные признаки
    Dataset<Row> datasetWithFeatures = this.inputData
        .orderBy(dateTime.asc())
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
        .withColumn("is_future", isnull(target))
        .withColumn("min_value_per_month", min(target).over(monthDataWindow))
        .withColumn("max_value_per_month", max(target).over(monthDataWindow))
        .withColumn("mean_value_per_month", mean(target).over(monthDataWindow))
        .withColumn("min_value_per_year", min(target).over(yearDataWindow))
        .withColumn("max_value_per_year", max(target).over(yearDataWindow))
        .withColumn("mean_value_per_year", mean(target).over(yearDataWindow));

    // Добавляем динамические признаки
    for (int i = 0; i < lags.size(); i++) {
      Integer lag = lags.get(i);
      String lagColName = getLagColName(lag);
      String trendColName = getTrendColName(lag);
      String movingAvgColName = getMovingAvgColName(lag);
      Column lagCol = col(lagColName);
      datasetWithFeatures = datasetWithFeatures
          .withColumn("temp", lag(target, lag).over(ascOrderedWindow))
          .withColumn(lagColName, when(col("temp").isNull(), lit(0)).otherwise(col("temp")))
          .drop("temp")
          .withColumn(movingAvgColName, avg(target).over(Window.rowsBetween(-1, 0)))
          .withColumn(trendColName, target.minus(lagCol));
    }

    return datasetWithFeatures
        .persist(StorageLevel.MEMORY_AND_DISK())
        .repartition(100, dateTime);
  }

  public static List<String> getFeatures(List<Integer> lags) {
    List<String> mainFeatures = new java.util.ArrayList<>(
        List.of("hour", "month", "year", "quarter", "day_of_week",
            "day_of_year", "day_of_month", "week_of_year", "is_weekend", "day_in_month",
            "min_value_per_month", "max_value_per_month", "mean_value_per_month",
            "min_value_per_year", "max_value_per_year", "mean_value_per_year"));

    lags.forEach(lag -> {
      mainFeatures.add(getLagColName(lag));
      mainFeatures.add(getTrendColName(lag));
      mainFeatures.add(getMovingAvgColName(lag));
    });
    return mainFeatures;
  }

  private static String getLagColName(Integer lag) {
    return String.format(lagColTemplate, lag);
  }

  private static String getTrendColName(Integer lag) {
    return String.format(trendColTemplate, lag);
  }

  private static String getMovingAvgColName(Integer lag) {
    return String.format(movingAvgColTemplate, lag);
  }
}
