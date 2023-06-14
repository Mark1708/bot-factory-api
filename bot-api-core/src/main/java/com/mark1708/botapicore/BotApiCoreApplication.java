package com.mark1708.botapicore;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableEurekaClient
@SpringBootApplication(
		scanBasePackages = {
				"com.mark1708.clients",
				"com.mark1708.botapicore",
		}
)
@OpenAPIDefinition(
		info = @Info(
				title = "Bot API Core",
				version = "1.0",
				description = "Core for connected bots"
		)
)
@EnableFeignClients(basePackages = {"com.mark1708.clients"})
public class BotApiCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BotApiCoreApplication.class, args);
	}

}
