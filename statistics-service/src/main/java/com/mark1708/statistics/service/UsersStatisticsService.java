package com.mark1708.statistics.service;

import com.mark1708.statistics.model.entity.StatisticsItem;
import java.util.List;

public interface UsersStatisticsService {

  List<StatisticsItem> getCountAllByBotId(long botId);

  List<StatisticsItem> getCountBlockedByBotId(long botId);

  List<StatisticsItem> getCountTodayOnlineByBotId(long botId);

  List<StatisticsItem> getCountTodayRegisteredByBotId(long botId);

  List<StatisticsItem> getCountNoPaymentsByBotId(long botId);

  List<StatisticsItem> getCountOnePaymentsByBotId(long botId);

  List<StatisticsItem> getCountFivePaymentsByBotId(long botId);

  List<StatisticsItem> getCountTenPaymentsByBotId(long botId);

  List<StatisticsItem> getCountActiveByBotId(long botId);
}
