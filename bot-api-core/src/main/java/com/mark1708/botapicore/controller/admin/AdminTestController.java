package com.mark1708.botapicore.controller.admin;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/admin")
public class AdminTestController {


  @GetMapping
  public ResponseEntity<String> get() {
    return ResponseEntity.of(Optional.of("admin"));
  }
}
