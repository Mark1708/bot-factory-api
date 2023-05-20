package com.mark1708.botapicore.converter;

import com.mark1708.botapicore.model.entity.SubService;
import com.mark1708.botapicore.model.enums.ServiceType;
import com.mark1708.botapicore.model.service.ServiceDto;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceConverter {

  private final TariffConverter tariffConverter;

  public ServiceDto toDto(SubService subService) {
    ServiceDto result = new ServiceDto();
    BeanUtils.copyProperties(subService, result);

    result.setType(subService.getType().equals(1) ?
        ServiceType.DATE_SUB : ServiceType.COUNTER_SUB);

    result.setTariffs(tariffConverter.toDto(subService.getTariffs()));

    return result;
  }

  public List<ServiceDto> toDto(Collection<SubService> subServices) {
    return subServices
        .stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }
}
