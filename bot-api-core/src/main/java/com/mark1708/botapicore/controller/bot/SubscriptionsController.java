package com.mark1708.botapicore.controller.bot;


import com.mark1708.botapicore.facade.SubscriptionFacade;
import com.mark1708.botapicore.model.subscription.SubscriptionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bot/subscriptions")
public class SubscriptionsController {

  private final SubscriptionFacade subscriptionFacade;

  @GetMapping("/{id}")
  public SubscriptionDto getSubscription(
      @RequestHeader("Authorization") String apiKey,
      @PathVariable Long id
  ) {
    log.debug("Get subscription: [{}]", id);
    return subscriptionFacade.getSubscription(apiKey, id);
  }


  @PutMapping("/{id}")
  public SubscriptionDto updateSubscriptionInfo(
      @RequestHeader("Authorization") String apiKey,
      @PathVariable Long id,
      @RequestBody SubscriptionDto subscriptionDto
  ) {
    log.debug("Update subscription info: [{}, {}]", id, subscriptionDto);
    return subscriptionFacade.updateSubscription(apiKey, id, subscriptionDto);
  }

  @DeleteMapping("/{id}")
  public boolean deleteSubscription(
      @RequestHeader("Authorization") String apiKey,
      @PathVariable Long id
  ) {
    log.debug("Delete subscription: [{}]", id);
    return subscriptionFacade.deleteSubscription(apiKey, id);
  }
}
