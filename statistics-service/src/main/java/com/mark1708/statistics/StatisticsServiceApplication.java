package com.mark1708.statistics;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Statistics API",
				version = "1.0",
				description = "Service for processing statistics data"
		)
)
public class StatisticsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatisticsServiceApplication.class, args);
	}

}
