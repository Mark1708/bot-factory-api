package com.mark1708.prediction.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("statistics-db")
public class StatisticsDbConfiguration {

  private String url;
  private String username;
  private String password;
  private String driverClassName;

}
