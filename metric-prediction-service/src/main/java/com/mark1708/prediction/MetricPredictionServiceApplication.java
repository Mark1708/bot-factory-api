package com.mark1708.prediction;

import com.mark1708.prediction.service.SparkMLService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class MetricPredictionServiceApplication {

  private final SparkSession sparkSession;
  private final SparkMLService sparkMLService;

  public static void main(String[] args) {
    SpringApplication.run(MetricPredictionServiceApplication.class, args);
  }

  @Bean
  public CommandLineRunner CommandLineRunnerBean() {
    return (args) -> {

      StructType schema = new StructType()
          .add("date_time", "timestamp")
          .add("target", "double");

      Dataset<Row> originalData = sparkSession.read().format("csv")
          .option("sep", ",")
          .option("inferSchema", "true")
          .option("header", "true")
          .schema(schema)
          .load("src/main/resources/PJME_hourly.csv");

      List<PredictedItem> result = sparkMLService.predictData("testModel", originalData, true);
      result.forEach(item -> {
        log.info("{}\t{}", item.getTimeStamp(), item.getValue());
      });
    };
  }
}
