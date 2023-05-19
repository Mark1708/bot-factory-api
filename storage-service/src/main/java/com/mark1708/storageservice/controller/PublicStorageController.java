package com.mark1708.storageservice.controller;

import com.mark1708.storageservice.facade.StorageFacade;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/public")
public class PublicStorageController {

  private final StorageFacade storageFacade;

  @GetMapping("/{token:.+}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String token) {
    log.debug("find file for download");
    return storageFacade.getResourceByToken(token);
  }
}
