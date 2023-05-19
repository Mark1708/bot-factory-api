package com.mark1708.prediction;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@Slf4j
@EnableScheduling
@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Metric Prediction API",
        version = "1.0",
        description = "Service for predicting metrics"
    )
)
public class MetricPredictionServiceApplication {


  public static void main(String[] args) {
    SpringApplication.run(MetricPredictionServiceApplication.class, args);
  }
}
