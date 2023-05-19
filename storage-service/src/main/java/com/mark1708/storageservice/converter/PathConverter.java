package com.mark1708.storageservice.converter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class PathConverter implements Converter<String, Path> {


  @Override
  @SneakyThrows
  public Path convert(@NonNull String source) {
    Path path = Paths.get(".").toAbsolutePath().normalize().resolve(source);
    if (!Files.exists(path)) {
      Files.createDirectories(path);
    }
    return path;
  }
}
