package com.mark1708.botapicore.model.tariff;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mark1708.botapicore.model.enums.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class TariffDto {

  private Long id;
  private TimeUnit timeUnit;
  private Long value;
  private Long price;
}
