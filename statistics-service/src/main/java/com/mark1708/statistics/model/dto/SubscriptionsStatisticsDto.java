package com.mark1708.statistics.model.dto;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionsStatisticsDto {

  private LocalDateTime dateTime;
  private Long botId;
  private Long serviceId;
  private Long countAll;
  private Long countActive;
}
