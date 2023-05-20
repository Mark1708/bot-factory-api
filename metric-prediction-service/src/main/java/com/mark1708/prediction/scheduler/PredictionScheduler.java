package com.mark1708.prediction.scheduler;

import cats.kernel.instances.order;
import com.mark1708.prediction.exception.PredictionException;
import com.mark1708.prediction.exception.http.BadRequestException;
import com.mark1708.prediction.model.Order;
import com.mark1708.clients.prediction.dto.PredictedItem;
import com.mark1708.prediction.service.OrderService;
import com.mark1708.prediction.service.StatisticsService;
import com.mark1708.prediction.service.TimeSeriesForecastService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PredictionScheduler {

  private final OrderService orderService;
  private final StatisticsService statisticsService;
  private final TimeSeriesForecastService forecastService;

  private boolean isProcessing = false;

  @Scheduled(fixedDelay = 10000)
  public void processing() {
    if (!isProcessing) {
      Optional<Order> orderOpt = orderService.findLastWaitingOrder();
      if (orderOpt.isPresent()) {
        isProcessing = true;
        Order order = orderOpt.get();
        log.info("Start forecasting with order id [{}]", order.getId());
        order.setStartAt(LocalDateTime.now());
        order.setStatus(1);
        order = orderService.saveOrder(order);
        try {
          Dataset<Row> originalData = getData(order);
          if (originalData.count() > 30) {
            orderService.updateStatus(order.getId(), 2);
            try {
              List<PredictedItem> result = forecastService.predict(originalData, order.getId(),
                  order.getDays());
              log.info("{}", result);
              order.setStatus(3);
              order.setEndAt(LocalDateTime.now());
              order.setResult(result);
              orderService.saveOrder(order);
            } catch (PredictionException e) {
              e.printStackTrace();
              orderService.updateStatus(order.getId(), 4);
            }
          } else {
            orderService.updateStatus(order.getId(), 5);
          }
        } catch (BadRequestException e) {
          e.printStackTrace();
          orderService.updateStatus(order.getId(), 4);
        }
        isProcessing = false;
      }
    }
  }

  private Dataset<Row> getData(Order order) {
    switch (order.getType()) {
      case "users":
        switch (order.getValue()) {
          case "count_all":
          case "count_blocked":
          case "count_today_online":
          case "count_today_registered":
          case "count_with_no_payments":
          case "count_with_more_one_payments":
          case "count_with_more_five_payments":
          case "count_with_more_ten_payments":
            return statisticsService.getUsersByCol(order.getBotId(), order.getValue());
          case "active":
            return statisticsService.getActiveUsers(order.getBotId());
          default:
            throw new BadRequestException(String.format("Unrecognized value [%s] of type [%s]", order.getValue(), order.getType()));
        }
      case "pays":
        switch (order.getValue()) {
          case "count_all":
          case "all_income":
          case "count_today":
          case "today_income":
            return statisticsService.getPaysByCol(
                order.getBotId(), order.getServiceId(), order.getValue()
            );
          case "count_all_by_tariff":
          case "all_income_by_tariff":
          case "count_today_by_tariff":
          case "today_income_by_tariff":
            return statisticsService.getPaysByCol(
                order.getBotId(), order.getServiceId(),
                order.getTariffId(), order.getValue()
            );
          default:
            throw new BadRequestException(String.format("Unrecognized value [%s] of type [%s]",
                    order.getValue(), order.getType())
            );
        }
      case "service_subscriptions":
        switch (order.getValue()) {
          case "count_all":
          case "count_active":
            return statisticsService.getServiceSubsByCol(
                order.getBotId(), order.getServiceId(), order.getValue()
            );
          default:
            throw new BadRequestException(String.format("Unrecognized value [%s] of type [%s]",
                order.getValue(), order.getType()));
        }
      default:
        throw new BadRequestException(String.format("Unrecognized type [%s]", order.getType()));
    }
  }

  private Dataset<Row> getData(Long botId, String type, String value) {
    switch (type) {
      case "users":
        switch (value) {
          case "count_all":
          case "count_blocked":
          case "count_today_online":
          case "count_today_registered":
          case "count_with_no_payments":
          case "count_with_more_one_payments":
          case "count_with_more_five_payments":
          case "count_with_more_ten_payments":
            return statisticsService.getUsersByCol(botId, value);
          case "active":
            return statisticsService.getActiveUsers(botId);
          default:
            throw new BadRequestException(String.format("Unrecognized value [%s] of type [%s]", value, type));
        }
      case "pays":
        switch (value) {
          case "count_with_more_ten_payments":
            return statisticsService.getUsersByCol(botId, value);
          default:
            throw new BadRequestException(String.format("Unrecognized value [%s] of type [%s]", value, type));
        }
      case "service_subscriptions":
        switch (value) {
          case "count_all":
          case "count_active":
            return statisticsService.getUsersByCol(botId, value);
          default:
            throw new BadRequestException(String.format("Unrecognized value [%s] of type [%s]", value, type));
        }
      default:
        throw new BadRequestException(String.format("Unrecognized type [%s]", type));
    }
  }
}
