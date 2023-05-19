package com.mark1708.storageservice.converter;

import com.mark1708.storageservice.exception.http.ResourceNotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResourceConverter {

  public ResponseEntity<Resource> toDto(Path path) {
    try {
      HttpHeaders header = new HttpHeaders();
      header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + path.getFileName());
      header.add("Cache-Control", "no-cache, no-store, must-revalidate");
      header.add("Pragma", "no-cache");
      header.add("Expires", "0");
      File file = path.toFile();
      String mimeType = null;
      try {
        mimeType = Files.probeContentType(path);
      } catch (IOException e) {
        e.printStackTrace();
        mimeType = "application/octet-stream";
      }
      InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
      return ResponseEntity.ok()
          .contentType(MediaType.parseMediaType(mimeType))
          .headers(header)
          .body(resource);
    } catch (ResourceNotFoundException | FileNotFoundException e) {
      e.printStackTrace();
      return ResponseEntity.noContent().build();
    }
  }
}
