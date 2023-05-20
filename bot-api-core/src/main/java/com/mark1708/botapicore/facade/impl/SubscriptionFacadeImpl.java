package com.mark1708.botapicore.facade.impl;

import com.mark1708.botapicore.converter.SubscriptionConverter;
import com.mark1708.botapicore.facade.SubscriptionFacade;
import com.mark1708.botapicore.model.entity.Bot;
import com.mark1708.botapicore.model.entity.Subscription;
import com.mark1708.botapicore.model.subscription.SubscriptionDto;
import com.mark1708.botapicore.service.BotService;
import com.mark1708.botapicore.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionFacadeImpl implements SubscriptionFacade {

  private final SubscriptionService subscriptionService;
  private final BotService botService;

  private final SubscriptionConverter subscriptionConverter;

  @Override
  public SubscriptionDto getSubscription(String apiKey, Long id) {
    Bot bot = botService.getBotByApiKey(apiKey);
    return subscriptionConverter.toDto(
        subscriptionService.getSubscriptionByIdAndBotId(id, bot.getId())
    );
  }

  @Override
  public SubscriptionDto updateSubscription(
      String apiKey,
      Long id,
      SubscriptionDto subscriptionDto
  ) {
    Bot bot = botService.getBotByApiKey(apiKey);
    Subscription subscription = subscriptionService.getSubscriptionByIdAndBotId(id, bot.getId());
    BeanUtils.copyProperties(subscriptionDto, subscription, "id");
    return subscriptionConverter.toDto(
        subscriptionService.saveSubscription(subscription)
    );
  }

  @Override
  public boolean deleteSubscription(String apiKey, Long id) {
    Bot bot = botService.getBotByApiKey(apiKey);
    return subscriptionService.deleteSubscriptionByIdAndBotId(id, bot.getId());
  }
}
