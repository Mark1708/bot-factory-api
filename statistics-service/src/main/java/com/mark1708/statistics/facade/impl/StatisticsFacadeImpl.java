package com.mark1708.statistics.facade.impl;

import com.mark1708.statistics.converter.PaysStatisticsConverter;
import com.mark1708.statistics.converter.SubscriptionsStatisticsDtoConverter;
import com.mark1708.statistics.converter.TimeSeriesItemConverter;
import com.mark1708.statistics.converter.UsersStatisticsConverter;
import com.mark1708.clients.statistics.dto.TimeSeriesItemDto;
import com.mark1708.statistics.exception.http.BadRequestException;
import com.mark1708.statistics.facade.StatisticsFacade;
import com.mark1708.statistics.service.PaysStatisticsService;
import com.mark1708.statistics.service.SubscriptionsStatisticsService;
import com.mark1708.statistics.service.UsersStatisticsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticsFacadeImpl implements StatisticsFacade {

  private final SubscriptionsStatisticsService subscriptionsService;
  private final UsersStatisticsService usersService;
  private final PaysStatisticsService paysService;

  private final SubscriptionsStatisticsDtoConverter statisticsDtoConverter;
  private final UsersStatisticsConverter usersDtoConverter;
  private final PaysStatisticsConverter paysDtoConverter;
  private final TimeSeriesItemConverter timeSeriesItemConverter;


  @Override
  public List<TimeSeriesItemDto> getUsersStatisticsByQuery(long botId, String query) {
    switch (query) {
      case "all":
        return timeSeriesItemConverter.toDto(
            usersService.getCountAllByBotId(botId)
        );
      case "blocked":
        return timeSeriesItemConverter.toDto(
            usersService.getCountBlockedByBotId(botId)
        );
      case "active":
        return timeSeriesItemConverter.toDto(
            usersService.getCountActiveByBotId(botId)
        );
      case "today_online":
        return timeSeriesItemConverter.toDto(
            usersService.getCountTodayOnlineByBotId(botId)
        );
      case "today_reg":
        return timeSeriesItemConverter.toDto(
            usersService.getCountTodayRegisteredByBotId(botId)
        );
      case "no_pay":
        return timeSeriesItemConverter.toDto(
            usersService.getCountNoPaymentsByBotId(botId)
        );
      case "one_pay":
        return timeSeriesItemConverter.toDto(
            usersService.getCountOnePaymentsByBotId(botId)
        );
      case "five_pay":
        return timeSeriesItemConverter.toDto(
            usersService.getCountFivePaymentsByBotId(botId)
        );
      case "ten_pay":
        return timeSeriesItemConverter.toDto(
            usersService.getCountTenPaymentsByBotId(botId)
        );
      default:
        throw new BadRequestException("Unknown query - " + query);
    }
  }
}
