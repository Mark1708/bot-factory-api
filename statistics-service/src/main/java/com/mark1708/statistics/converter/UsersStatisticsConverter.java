package com.mark1708.statistics.converter;

import com.mark1708.statistics.model.dto.UsersStatisticsDto;
import com.mark1708.statistics.model.entity.UsersStatistics;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UsersStatisticsConverter {

  public UsersStatisticsDto toDto(UsersStatistics usersStatistics) {
    UsersStatisticsDto result = new UsersStatisticsDto();
    BeanUtils.copyProperties(usersStatistics, result);
    return result;
  }

  public List<UsersStatisticsDto> toDto(Collection<UsersStatistics> usersStatisticsCollection) {
    return usersStatisticsCollection
        .stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }
}
