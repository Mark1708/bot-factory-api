package com.mark1708.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class GatewayServerApplication {
	// TODO: придумать что-то с Spring Cloud Sleuth в Gateway (он не работает)
	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}
}
