package com.mark1708.botapicore.converter;

import com.mark1708.botapicore.model.entity.Pay;
import com.mark1708.botapicore.model.pay.PayDto;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PayConverter {

  public PayDto toDto(Pay pay) {
    PayDto result = new PayDto();
    BeanUtils.copyProperties(pay, result);

    result.setUserId(pay.getUser().getId());
    result.setServiceId(pay.getService().getId());
    result.setTariffId(pay.getTariff().getId());

    return result;
  }

  public List<PayDto> toDto(Collection<Pay> pays) {
    return pays
        .stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }
}
