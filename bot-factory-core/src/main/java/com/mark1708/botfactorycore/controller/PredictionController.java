package com.mark1708.botfactorycore.controller;

import com.mark1708.clients.prediction.PredictionClient;
import com.mark1708.clients.prediction.dto.CreateOrderDto;
import com.mark1708.clients.prediction.dto.ResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/factory/statistics")
public class PredictionController {

  private final PredictionClient predictionClient;

  @GetMapping("/{id}")
  public ResultDto getResult(@PathVariable(name = "id") String id) {
    return predictionClient.getResult(id);
  }

  @PostMapping
  public ResultDto createOrder(@RequestBody CreateOrderDto createOrderDto) {
    return predictionClient.createOrder(createOrderDto);
  }
}
