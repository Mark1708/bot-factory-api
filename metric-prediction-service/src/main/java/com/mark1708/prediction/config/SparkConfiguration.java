package com.mark1708.prediction.config;

import static com.mark1708.prediction.util.UdfMethods.dayInMonth;
import static com.mark1708.prediction.util.UdfMethods.isWeekend;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.api.java.UDF0;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.api.java.UDF2;
import org.apache.spark.sql.types.DataTypes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SparkConfiguration {

  @Value("${spring.application.name}")
  private String appName;

  @Bean
  public SparkConf sparkConf() {
    log.info("SparkConf Bean");
    return new SparkConf(true)
        .setAppName(appName)
        .setMaster("local");
  }

  @Bean
  public JavaSparkContext javaSparkContext() {
    log.info("JavaSparkContext Bean");
    return new JavaSparkContext(sparkConf());
  }

  @Bean
  public SparkSession sparkSession() {
    log.info("SparkSession Bean");
    SparkSession sparkSession = SparkSession
        .builder()
        .appName(appName)
        .sparkContext(javaSparkContext().sc())
        .config(javaSparkContext().getConf())
        .getOrCreate();

    // Добавляем кастомные функции
    sparkSession
        .sqlContext().udf()
        .register("dayInMonth", dayInMonth(), DataTypes.IntegerType);

    sparkSession
        .sqlContext().udf()
        .register("isWeekend", isWeekend(), DataTypes.IntegerType);

    return sparkSession;
  }
}
