package com.mark1708.botapicore.controller.factory;

import com.mark1708.botapicore.model.bot.BotDto;
import com.mark1708.botapicore.model.bot.CreateBotDto;
import com.mark1708.botapicore.model.entity.Bot;
import com.mark1708.botapicore.repository.BotRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/factory")
public class FactoryTestController {

  private final BotRepository botRepository;


  @GetMapping
  public ResponseEntity<String> get() {
    return ResponseEntity.of(Optional.of("factory"));
  }

  @PostMapping
  public BotDto createBot(@RequestBody CreateBotDto createBotDto) {
    Bot bot = new Bot();
    BeanUtils.copyProperties(createBotDto, bot);
    Bot newBot = botRepository.saveAndFlush(bot);

    BotDto botDto = new BotDto();
    BeanUtils.copyProperties(newBot, botDto);
    return botDto;
  }

  @PutMapping("/{id}")
  public BotDto updateBot(@PathVariable Long id, @RequestBody BotDto botDto) {
    Bot bot = botRepository.findById(id).get();
    BeanUtils.copyProperties(botDto, bot);
    Bot newBot = botRepository.saveAndFlush(bot);

    BotDto newBotDto = new BotDto();
    BeanUtils.copyProperties(newBot, newBotDto);
    return newBotDto;
  }
}
