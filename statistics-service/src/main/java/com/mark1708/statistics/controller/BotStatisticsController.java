package com.mark1708.statistics.controller;

import com.mark1708.clients.statistics.dto.TimeSeriesItemDto;
import com.mark1708.statistics.facade.StatisticsFacade;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/bot")
public class BotStatisticsController {

  private final StatisticsFacade statisticsFacade;

  @GetMapping("/{botId}/users")
  @Operation(method = "Get statistics")
  public List<TimeSeriesItemDto> get(
      @PathVariable Long botId,
      @RequestParam(name = "query") String query
  ) {
    return statisticsFacade.getUsersStatisticsByQuery(botId, query);
  }

}
