package com.mark1708.botapicore.model.pay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePayDto {
  private Long userId;
  private Long tariffId;
  private String payload;
  private Long amount;
}
