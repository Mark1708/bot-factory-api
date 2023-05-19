package com.mark1708.botfactorycore.controller;

import com.mark1708.clients.statistics.StatisticsClient;
import com.mark1708.clients.statistics.dto.TimeSeriesItemDto;
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

  //// Статистика компании:
  // TODO: Статистика по компании (кол-во проектов, кол-во сотрудников, прибыль за месяц/год)
  // TODO: статистика по оплатам компании (месяц-рус, массив обектктов { название проекта, массив месячных доходов})


  //// Статистика проекта:
  // TODO: Статистика пользователей по проекту (кол-во пользователей, кол-во в новых за месяц, кол-во в среднем онлайн за месяц, заблокировали бота)
  // TODO: Статистика подписок по проекту (кол-во услуг, кол-во активных подписок, кол-во подписок за всё время)
  // TODO: Статистика оплат по проекту (кол-во/прибыль за месяц, кол-во/прибыль за год, кол-во пользователей опалтивших хоть раз)

  // TODO: статистика по кол-ву подписок всего/активно по услуге (месяц-рус, массив обектктов { название всего/активно, массив количестом за месяц})
  // TODO: статистика по сумме оплат (месяц-рус, массив обектктов { название всего/услуга 1/услуга 2, сумма})
  // TODO: статистика по кол-ву оплат (месяц-рус, массив обектктов { название всего/услуга 1/услуга 2, кол-во})
}
