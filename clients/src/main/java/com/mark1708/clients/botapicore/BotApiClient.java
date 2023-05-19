package com.mark1708.clients.botapicore;

import com.mark1708.clients.botapicore.dto.BotDto;
import com.mark1708.clients.botapicore.dto.CreateBotDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "bot-api-core", path = "/api/v1/factory")
public interface BotApiClient {

  @PostMapping
  BotDto createBot(@RequestBody CreateBotDto createBotDto);

  @PutMapping("/{id}")
  BotDto updateBot(@PathVariable(name = "id") Long id, @RequestBody BotDto botDto);

  @DeleteMapping("/{id}")
  boolean deleteBot(@PathVariable(name = "id") Long id);
}
