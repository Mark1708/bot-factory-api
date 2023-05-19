package com.mark1708.prediction.facade;

import com.mark1708.clients.prediction.dto.CreateOrderDto;
import com.mark1708.clients.prediction.dto.ResultDto;

public interface PredictionFacade {

  ResultDto createOrder(CreateOrderDto createOrderDto);

  ResultDto getResult(String id);
}
