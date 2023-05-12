package com.mark1708.botapicore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class BotApiCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BotApiCoreApplication.class, args);
	}

}
