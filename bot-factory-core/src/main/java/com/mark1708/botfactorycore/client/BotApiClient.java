package com.mark1708.botfactorycore.client;

import com.mark1708.botfactorycore.model.bot.BotDto;
import com.mark1708.botfactorycore.model.bot.CreateBotDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("bot-api-core")
public interface BotApiClient {

  @PostMapping("/api/v1/factory")
  BotDto createBot(@RequestBody CreateBotDto createBotDto);

  @PutMapping("/api/v1/factory/{id}")
  BotDto updateBot(@PathVariable(name = "id") Long id, @RequestBody BotDto botDto);
}
