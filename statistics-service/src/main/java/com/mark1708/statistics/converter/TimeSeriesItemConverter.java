package com.mark1708.statistics.converter;

import com.mark1708.clients.statistics.dto.TimeSeriesItemDto;
import com.mark1708.statistics.model.entity.StatisticsItem;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class TimeSeriesItemConverter {

  public TimeSeriesItemDto toDto(StatisticsItem item) {
    TimeSeriesItemDto result = new TimeSeriesItemDto(
        item.getDateTime(),
        item.getValue()
    );

    return result;
  }

  public List<TimeSeriesItemDto> toDto(Collection<StatisticsItem> items) {
    return items
        .stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }

}
