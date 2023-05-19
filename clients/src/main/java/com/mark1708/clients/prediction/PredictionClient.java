package com.mark1708.clients.prediction;

import com.mark1708.clients.prediction.dto.CreateOrderDto;
import com.mark1708.clients.prediction.dto.ResultDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "metric-prediction-service", path = "/api/v1/prediction")
public interface PredictionClient {

  @GetMapping("/{id}")
  ResultDto getResult(@PathVariable(name = "id") String id);

  @PostMapping
  ResultDto createOrder(@RequestBody CreateOrderDto createOrderDto);
}
