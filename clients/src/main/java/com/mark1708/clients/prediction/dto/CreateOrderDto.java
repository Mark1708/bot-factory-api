package com.mark1708.clients.prediction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDto {

  private Long botId;
  private Long serviceId;
  private Long tariffId;
  private Integer days;
  private String type;
  private String value;
}
