package com.mark1708.botapicore;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("bot-factory-core")
public interface TestTwoClient {

  @GetMapping("/api/v1/test")
  ResponseEntity<String> test();
}
