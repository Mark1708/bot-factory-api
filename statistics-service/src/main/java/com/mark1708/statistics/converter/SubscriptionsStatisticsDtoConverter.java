package com.mark1708.statistics.converter;

import com.mark1708.statistics.model.dto.SubscriptionsStatisticsDto;
import com.mark1708.statistics.model.entity.SubscriptionsStatistics;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionsStatisticsDtoConverter {

  public SubscriptionsStatisticsDto toDto(SubscriptionsStatistics subscriptionsStatistics) {
    SubscriptionsStatisticsDto result = new SubscriptionsStatisticsDto();
    BeanUtils.copyProperties(subscriptionsStatistics, result);
    return result;
  }

  public List<SubscriptionsStatisticsDto> toDto(
      Collection<SubscriptionsStatistics> subscriptionsStatistics) {
    return subscriptionsStatistics
        .stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }
}
