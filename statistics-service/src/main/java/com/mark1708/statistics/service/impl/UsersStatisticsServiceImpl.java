package com.mark1708.statistics.service.impl;

import com.mark1708.statistics.model.entity.StatisticsItem;
import com.mark1708.statistics.repository.UsersStatisticsRepository;
import com.mark1708.statistics.service.UsersStatisticsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersStatisticsServiceImpl implements UsersStatisticsService {

  private final UsersStatisticsRepository repository;

  @Override
  public List<StatisticsItem> getCountAllByBotId(long botId) {
    return repository.findCountAllByBotId(botId);
  }

  @Override
  public List<StatisticsItem> getCountBlockedByBotId(long botId) {
    return repository.findCountBlockedByBotId(botId);
  }

  @Override
  public List<StatisticsItem> getCountTodayOnlineByBotId(long botId) {
    return repository.findCountTodayOnlineByBotId(botId);
  }

  @Override
  public List<StatisticsItem> getCountTodayRegisteredByBotId(long botId) {
    return repository.findCountTodayRegisteredByBotId(botId);
  }

  @Override
  public List<StatisticsItem> getCountNoPaymentsByBotId(long botId) {
    return repository.findCountNoPaymentsByBotId(botId);
  }

  @Override
  public List<StatisticsItem> getCountOnePaymentsByBotId(long botId) {
    return repository.findCountOnePaymentsByBotId(botId);
  }

  @Override
  public List<StatisticsItem> getCountFivePaymentsByBotId(long botId) {
    return repository.findCountFivePaymentsByBotId(botId);
  }

  @Override
  public List<StatisticsItem> getCountTenPaymentsByBotId(long botId) {
    return repository.findCountTenPaymentsByBotId(botId);
  }

  @Override
  public List<StatisticsItem> getCountActiveByBotId(long botId) {
    return repository.findCountActiveByBotId(botId);
  }
}
