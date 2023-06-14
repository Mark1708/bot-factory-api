package com.mark1708.botapicore.service;

import com.mark1708.botapicore.model.entity.Subscription;
import java.util.List;

public interface SubscriptionService {

  List<Subscription> getSubscriptionsByBotIdAndServiceId(Long botId, Long serviceId);

  Subscription getSubscriptionByIdAndBotId(Long id, Long botId);

  Subscription saveSubscription(Subscription subscription);

  boolean deleteSubscriptionByIdAndBotId(Long id, Long botId);

  List<Subscription> getSubscriptionsByBotIdAndUserId(Long botId, Long userId);
}
