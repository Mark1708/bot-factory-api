package com.mark1708.botapicore.controller.factory;

import com.mark1708.botapicore.facade.BotFacade;
import com.mark1708.botapicore.model.pay.PayDto;
import com.mark1708.botapicore.model.service.ServiceDto;
import com.mark1708.botapicore.model.user.UserDto;
import com.mark1708.clients.botapicore.dto.BotDto;
import com.mark1708.clients.botapicore.dto.CreateBotDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api/v1/factory/bots")
public class FactoryBotController {

  private final BotFacade botFacade;

  @PostMapping
  public BotDto createBot(@RequestBody CreateBotDto createBotDto) {
    return botFacade.createBot(createBotDto);
  }

  @PutMapping("/{id}")
  public BotDto updateBot(@PathVariable Long id, @RequestBody BotDto botDto) {
    return botFacade.updateBot(id, botDto);
  }

  @DeleteMapping("/{id}")
  public boolean deleteBot(@PathVariable Long id) {
    return botFacade.deleteBot(id);
  }

  @GetMapping("/{id}/users")
  public List<UserDto> getBotUsers(@PathVariable Long id) {
    log.debug("Get bot's users: {}", id);
    return botFacade.getBotUsers(id);
  }

  @GetMapping("/{id}/pays")
  public List<PayDto> getBotPays(@PathVariable Long id) {
    log.debug("Get bot's pays: {}", id);
    return botFacade.getBotPays(id);
  }

  @GetMapping("/{id}/services")
  public List<ServiceDto> getBotServices(@PathVariable Long id) {
    log.debug("Get bot's services: {}", id);
    return botFacade.getBotServices(id);
  }
}
