package com.mark1708.storageservice.configuration;

import java.nio.file.Path;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("app.storage")
public class FileStorageConfiguration {


  private Path storageDir;

  private List<String> allowedExtension;
}
