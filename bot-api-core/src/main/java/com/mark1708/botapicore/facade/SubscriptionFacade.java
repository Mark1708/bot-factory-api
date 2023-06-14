package com.mark1708.botapicore.facade;

import com.mark1708.botapicore.model.subscription.SubscriptionDto;

public interface SubscriptionFacade {

  SubscriptionDto getSubscription(String apiKey, Long id);

  SubscriptionDto updateSubscription(String apiKey, Long id, SubscriptionDto subscriptionDto);

  boolean deleteSubscription(String apiKey, Long id);
}
