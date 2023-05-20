package com.mark1708.botapicore.facade.impl;

import com.mark1708.botapicore.converter.BotConverter;
import com.mark1708.botapicore.converter.PayConverter;
import com.mark1708.botapicore.converter.ServiceConverter;
import com.mark1708.botapicore.converter.UserConverter;
import com.mark1708.botapicore.facade.BotFacade;
import com.mark1708.botapicore.model.entity.Bot;
import com.mark1708.botapicore.model.pay.PayDto;
import com.mark1708.botapicore.model.service.ServiceDto;
import com.mark1708.botapicore.model.user.UserDto;
import com.mark1708.botapicore.service.BotService;
import com.mark1708.botapicore.service.PayService;
import com.mark1708.botapicore.service.ServiceService;
import com.mark1708.botapicore.service.UserService;
import com.mark1708.clients.botapicore.dto.BotDto;
import com.mark1708.clients.botapicore.dto.CreateBotDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BotFacadeImpl implements BotFacade {

  private final BotService botService;
  private final UserService userService;
  private final PayService payService;
  private final ServiceService serviceService;

  private final BotConverter botConverter;
  private final UserConverter userConverter;
  private final PayConverter payConverter;
  private final ServiceConverter serviceConverter;

  @Override
  public BotDto createBot(CreateBotDto createBotDto) {
    Bot bot = Bot.builder()
        .companyId(createBotDto.getCompanyId())
        .apiKey(createBotDto.getApiKey())
        .webhookPath(createBotDto.getWebhookPath())
        .active(true)
        .build();
    return botConverter.toDto(
      botService.saveBot(bot)
    );
  }

  @Override
  public BotDto updateBot(Long id, BotDto botDto) {
    Bot bot = botService.getBotById(id);
    BeanUtils.copyProperties(botDto, bot, "id");
    return botConverter.toDto(
        botService.saveBot(bot)
    );
  }

  @Override
  public boolean deleteBot(Long id) {
    return botService.deleteBotById(id);
  }

  @Override
  public List<UserDto> getBotUsers(Long id) {
    return userConverter.toDto(
        userService.getUsersByBotId(id)
    );
  }

  @Override
  public List<PayDto> getBotPays(Long id) {
    return payConverter.toDto(
      payService.getPaysByBotId(id)
    );
  }

  @Override
  public List<ServiceDto> getBotServices(Long id) {
    return serviceConverter.toDto(
        serviceService.getServicesByBotId(id)
    );
  }
}
