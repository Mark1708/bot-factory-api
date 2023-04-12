package com.mark1708.botfactorycore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BotFactoryCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BotFactoryCoreApplication.class, args);
	}

}
