package com.mark1708.botapicore.model.subscription;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mark1708.botapicore.utils.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class SubscriptionDto {

  private Long id;
  private boolean active;
  private boolean notified;
  private boolean needNotify;
  private boolean subscribeAfterTrial;

  private Long trialCount;
  private Long availableCount;

  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime trialPeriodStartDate;

  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime trialPeriodEndDate;

  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime endDate;
}
