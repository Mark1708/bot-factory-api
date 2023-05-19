package com.mark1708.botapicore.converter;

import com.mark1708.botapicore.model.entity.Service;
import com.mark1708.botapicore.model.enums.ServiceType;
import com.mark1708.botapicore.model.service.ServiceDto;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ServiceConverter {

  public ServiceDto toDto(Service service) {
    ServiceDto result = new ServiceDto();
    BeanUtils.copyProperties(service, result);

    result.setType(service.getType().equals(1) ?
        ServiceType.DATE_SUB : ServiceType.COUNTER_SUB);


    return result;
  }

  public List<ServiceDto> toDto(Collection<Service> services) {
    return services
        .stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }
}
