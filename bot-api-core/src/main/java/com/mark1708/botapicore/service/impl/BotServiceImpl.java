package com.mark1708.botapicore.service.impl;

import com.mark1708.botapicore.exception.http.QueryType;
import com.mark1708.botapicore.exception.http.ResourceNotFoundException;
import com.mark1708.botapicore.exception.http.ResourceType;
import com.mark1708.botapicore.model.entity.Bot;
import com.mark1708.botapicore.repository.BotRepository;
import com.mark1708.botapicore.service.BotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BotServiceImpl implements BotService {

  private final BotRepository repository;

  @Override
  public Bot getBotByApiKey(String apiKey) {
    return repository.findBotByApiKey(apiKey)
        .orElseThrow(() ->  new ResourceNotFoundException(ResourceType.BOT, QueryType.API_KEY, apiKey));
  }

  @Override
  public boolean isValidKey(String apiKey) {
    return repository.findBotByApiKey(apiKey).isPresent();
  }
}
