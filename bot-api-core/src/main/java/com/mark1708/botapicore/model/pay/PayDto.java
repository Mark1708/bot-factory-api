package com.mark1708.botapicore.model.pay;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mark1708.botapicore.utils.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayDto {

  private Long id;
  private Long userId;
  private Long serviceId;
  private Long tariffId;

  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime payDate;
  private String payload;
  private Long amount;
}
