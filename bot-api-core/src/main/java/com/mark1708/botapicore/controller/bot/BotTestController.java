package com.mark1708.botapicore.controller.bot;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/bot")
public class BotTestController {


  @GetMapping
  public ResponseEntity<String> get(@RequestHeader("Authorization") String apiKey) {
    log.info(apiKey);
    return ResponseEntity.of(Optional.of("bot"));
  }
}
