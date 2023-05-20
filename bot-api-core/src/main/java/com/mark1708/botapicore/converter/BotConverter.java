package com.mark1708.botapicore.converter;

import com.mark1708.botapicore.model.entity.Bot;
import com.mark1708.clients.botapicore.dto.BotDto;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class BotConverter {

  public BotDto toDto(Bot bot) {
    BotDto result = new BotDto();
    BeanUtils.copyProperties(bot, result);


    return result;
  }

  public List<BotDto> toDto(Collection<Bot> bots) {
    return bots
        .stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }
}
