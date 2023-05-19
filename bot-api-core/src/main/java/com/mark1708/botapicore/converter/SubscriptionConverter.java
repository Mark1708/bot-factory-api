package com.mark1708.botapicore.converter;

import com.mark1708.botapicore.model.entity.Subscription;
import com.mark1708.botapicore.model.subscription.SubscriptionDto;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionConverter {

  public SubscriptionDto toDto(Subscription subscription) {
    SubscriptionDto result = new SubscriptionDto();
    BeanUtils.copyProperties(subscription, result);


    return result;
  }

  public List<SubscriptionDto> toDto(Collection<Subscription> subscriptions) {
    return subscriptions
        .stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }
}
