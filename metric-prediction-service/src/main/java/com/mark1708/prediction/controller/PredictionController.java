package com.mark1708.prediction.controller;

import com.mark1708.clients.prediction.dto.CreateOrderDto;
import com.mark1708.clients.prediction.dto.ResultDto;
import com.mark1708.prediction.facade.PredictionFacade;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/api/v1/prediction")
public class PredictionController {

  private final PredictionFacade predictionFacade;

  @GetMapping ("/{id}")
  @Operation(method = "Get result of prediction")
  public ResultDto getResult(@PathVariable String id) {
    return predictionFacade.getResult(id);
  }

  @PostMapping
  @Operation(method = "Create order for prediction")
  public ResultDto createOrder(@RequestBody CreateOrderDto createOrderDto) {
    return predictionFacade.createOrder(createOrderDto);
  }
}
