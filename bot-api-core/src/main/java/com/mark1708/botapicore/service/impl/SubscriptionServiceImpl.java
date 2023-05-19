package com.mark1708.botapicore.service.impl;

import com.mark1708.botapicore.repository.SubscriptionRepository;
import com.mark1708.botapicore.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

  private final SubscriptionRepository repository;

}
