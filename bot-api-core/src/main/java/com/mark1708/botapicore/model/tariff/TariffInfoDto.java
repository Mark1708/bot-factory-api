package com.mark1708.botapicore.model.tariff;

import com.mark1708.botapicore.model.enums.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TariffInfoDto {


  private TimeUnit timeUnit;
  private Long value;
  private Long price;
}
