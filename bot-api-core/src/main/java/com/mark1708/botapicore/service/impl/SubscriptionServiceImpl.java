package com.mark1708.botapicore.service.impl;

import com.mark1708.botapicore.exception.http.QueryType;
import com.mark1708.botapicore.exception.http.ResourceNotFoundException;
import com.mark1708.botapicore.exception.http.ResourceType;
import com.mark1708.botapicore.model.entity.Subscription;
import com.mark1708.botapicore.repository.SubscriptionRepository;
import com.mark1708.botapicore.service.SubscriptionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

  private final SubscriptionRepository repository;

  @Override
  public List<Subscription> getSubscriptionsByBotIdAndServiceId(Long botId, Long serviceId) {
    return repository.findSubscriptionsByBotIdAndServiceId(botId, serviceId);
  }

  @Override
  public List<Subscription> getSubscriptionsByBotIdAndUserId(Long botId, Long userId) {
    return repository.findSubscriptionsByBotIdAndUserId(botId, userId);
  }

  @Override
  public Subscription getSubscriptionByIdAndBotId(Long id, Long botId) {
    return repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(ResourceType.SUB, QueryType.ID, id));
  }

  @Override
  public Subscription saveSubscription(Subscription subscription) {
    return repository.saveAndFlush(subscription);
  }

  @Override
  public boolean deleteSubscriptionByIdAndBotId(Long id, Long botId) {
    try {
      repository.deleteById(id);
      return true;
    } catch (Exception e) {
      throw new ResourceNotFoundException(ResourceType.SUB, QueryType.ID, id);
    }
  }
}
