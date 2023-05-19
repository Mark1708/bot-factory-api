package com.mark1708.statistics.model.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersStatisticsDto {

  private LocalDateTime dateTime;

  private Long botId;

  private Long countAll;

  private Long countBlocked;

  private Long countTodayOnline;

  private Long countTodayRegistered;

  private Long countWithNoPayments;

  private Long countWithMoreOnePayments;

  private Long countWithMoreFivePayments;

  private Long countWithMoreTenPayments;

}
