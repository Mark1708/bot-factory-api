package com.mark1708.botfactorycore.controller;

import com.mark1708.clients.statistics.StatisticsClient;
import com.mark1708.clients.statistics.dto.TimeSeriesItemDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/factory/statistics")
@OpenAPIDefinition(
    info = @Info(
        title = "Statistics Controller",
        version = "1.0",
        description = "Controller for getting statistics"
    )
)
public class StatisticsController {

  private final StatisticsClient statisticsClient;

  @GetMapping("/{botId}/users")
  public List<TimeSeriesItemDto> getUsersStatistics(
      @PathVariable Long botId,
      @RequestParam(name = "query") String query
  ) {
    log.info("Get users statistics [{}, {}]", botId, query);
    return statisticsClient.getUsersStatistics(botId, query);
  }

  // TODO: статистика по оплатам компании (месяц-рус, массив обектктов { название проекта, массив месячных доходов})

  // TODO: оплаты для таблицы - список (дата, название проекта, название услуги, сумма)

  //// Статистика проекта:
  // TODO: статистика по кол-ву подписок всего/активно по услуге (месяц-рус, массив обектктов { название всего/активно, массив количестом за месяц})
  // TODO: статистика по сумме оплат (месяц-рус, массив обектктов { название всего/услуга 1/услуга 2, сумма})
  // TODO: статистика по кол-ву оплат (месяц-рус, массив обектктов { название всего/услуга 1/услуга 2, кол-во})
}
