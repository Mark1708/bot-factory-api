package com.mark1708.botapicore.converter;

import com.mark1708.botapicore.model.entity.Tariff;
import com.mark1708.botapicore.model.tariff.TariffDto;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TariffConverter {

  public TariffDto toDto(Tariff tariff) {
    TariffDto result = new TariffDto();
    BeanUtils.copyProperties(tariff, result);


    return result;
  }

  public List<TariffDto> toDto(Collection<Tariff> tariffs) {
    return tariffs
        .stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }
}
