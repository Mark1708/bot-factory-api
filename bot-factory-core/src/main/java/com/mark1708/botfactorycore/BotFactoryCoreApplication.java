package com.mark1708.botfactorycore;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.mark1708")
@EnableEurekaClient
@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Bot Factory Core",
        version = "1.0",
        description = "Core for web app and managing bots"
    )
)
public class BotFactoryCoreApplication {

  public static void main(String[] args) {
    SpringApplication.run(BotFactoryCoreApplication.class, args);
  }
}
