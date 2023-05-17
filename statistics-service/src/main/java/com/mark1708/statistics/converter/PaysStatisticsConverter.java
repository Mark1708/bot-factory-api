package com.mark1708.statistics.converter;

import com.mark1708.statistics.model.dto.PaysStatisticsDto;
import com.mark1708.statistics.model.entity.PaysStatistics;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PaysStatisticsConverter {

  public PaysStatisticsDto toDto(PaysStatistics paysStatistics) {
    PaysStatisticsDto result = new PaysStatisticsDto();
    BeanUtils.copyProperties(paysStatistics, result);
    return result;
  }

  public List<PaysStatisticsDto> toDto(Collection<PaysStatistics> paysStatisticsCollection) {
    return paysStatisticsCollection
        .stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }
}
