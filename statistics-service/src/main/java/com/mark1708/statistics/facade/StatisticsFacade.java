package com.mark1708.statistics.facade;

import com.mark1708.clients.statistics.dto.TimeSeriesItemDto;
import java.util.List;

public interface StatisticsFacade {

  List<TimeSeriesItemDto> getUsersStatisticsByQuery(long botId, String query);
}
