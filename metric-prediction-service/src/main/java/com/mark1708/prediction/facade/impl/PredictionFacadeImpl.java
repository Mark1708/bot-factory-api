package com.mark1708.prediction.facade.impl;

import com.mark1708.prediction.exception.http.BadRequestException;
import com.mark1708.prediction.facade.PredictionFacade;
import com.mark1708.clients.prediction.dto.CreateOrderDto;
import com.mark1708.clients.prediction.dto.ResultDto;
import com.mark1708.prediction.model.Order;
import com.mark1708.clients.prediction.dto.PredictedItem;
import com.mark1708.prediction.service.OrderService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PredictionFacadeImpl implements PredictionFacade {

  private final OrderService orderService;

  @Override
  public ResultDto getResult(String id) {
    Order order = orderService.getOrderById(id);

    String status = "";
    List<PredictedItem> result = null;
    switch (order.getStatus()) {
      case 0:
        status = "waiting";
        break;
      case 1:
        status = "retrieving";
        break;
      case 2:
        status = "forecasting";
        break;
      case 3:
        status = "done";
        result = order.getResult();
        break;
      case 4:
        status = "error";
        break;
      case 5:
        status = "small";
        break;
    }

    return ResultDto.builder()
        .id(order.getId())
        .status(status)
        .predictedItems(result)
        .build();
  }

  @Override
  public ResultDto createOrder(CreateOrderDto createOrderDto) {

    Order order = orderService.saveOrder(
        Order.builder()
            .botId(createOrderDto.getBotId())
            .serviceId(createOrderDto.getServiceId())
            .tariffId(createOrderDto.getTariffId())
            .days(createOrderDto.getDays())
            .type(createOrderDto.getType())
            .value(createOrderDto.getValue())
            .createdAt(LocalDateTime.now())
            .status(0)
            .build()
    );
    return ResultDto.builder()
        .id(order.getId())
        .status("waiting")
        .build();
  }
}
