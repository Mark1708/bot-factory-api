package com.mark1708.botapicore.service;

import com.mark1708.botapicore.model.entity.Bot;

public interface BotService {

  Bot getBotByApiKey(String apiKey);

  boolean isValidKey(String apiKey);

  Bot saveBot(Bot bot);

  Bot getBotById(Long id);

  boolean deleteBotById(Long id);
}
