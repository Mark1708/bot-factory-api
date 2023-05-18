package com.mark1708.prediction.util;

import static org.apache.spark.sql.functions.max;
import static org.apache.spark.sql.functions.min;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.types.StructType;

public class PredictionDataGenerator {

  private String dateTimeName;
  private final String targetName;
  private final SparkSession sparkSession;

  public PredictionDataGenerator(SparkSession sparkSession, String dateTimeName, String targetName) {
    this.sparkSession = sparkSession;
    this.dateTimeName = dateTimeName;
    this.targetName = targetName;
  }

  public Dataset<Row> generate(LocalDateTime fromDate, LocalDateTime toDate) {
    // Генерируем данные
    long numOfDaysBetween = ChronoUnit.DAYS.between(fromDate, toDate);
    List<Row> dates = IntStream.iterate(0, i -> i + 1)
        .limit(numOfDaysBetween)
        .mapToObj(fromDate::plusDays)
        .map(Timestamp::valueOf)
        .map(RowFactory::create)
        .collect(Collectors.toList());

    StructType schema = new StructType()
        .add(dateTimeName, "timestamp");

    return sparkSession.createDataFrame(dates, schema)
        .withColumn(targetName, functions.lit(null));
  }
}
