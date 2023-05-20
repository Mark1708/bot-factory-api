package com.mark1708.botapicore.model.service;

import com.mark1708.botapicore.model.enums.ServiceType;
import com.mark1708.botapicore.model.tariff.TariffDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDto {

  private Long id;
  private ServiceType type;
  private String name;
  private List<TariffDto> tariffs;
}
