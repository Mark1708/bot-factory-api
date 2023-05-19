package com.mark1708.prediction.configuration;

import static com.mark1708.prediction.util.UdfMethods.dayInMonth;
import static com.mark1708.prediction.util.UdfMethods.isWeekend;

import lombok.extern.slf4j.Slf4j;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Slf4j
@Configuration
public class ForecastSparkConfiguration {

  @Value("${spring.application.name}")
  private String appName;

  @Bean
  @Primary
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
