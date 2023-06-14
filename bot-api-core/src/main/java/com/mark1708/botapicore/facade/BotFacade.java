package com.mark1708.botapicore.facade;

import com.mark1708.botapicore.model.pay.PayDto;
import com.mark1708.botapicore.model.service.ServiceDto;
import com.mark1708.botapicore.model.user.UserDto;
import com.mark1708.clients.botapicore.dto.BotDto;
import com.mark1708.clients.botapicore.dto.CreateBotDto;
import java.util.List;

public interface BotFacade {

  BotDto createBot(CreateBotDto createBotDto);

  BotDto updateBot(Long id, BotDto botDto);

  boolean deleteBot(Long id);

  List<UserDto> getBotUsers(Long id);

  List<PayDto> getBotPays(Long id);

  List<ServiceDto> getBotServices(Long id);
}
