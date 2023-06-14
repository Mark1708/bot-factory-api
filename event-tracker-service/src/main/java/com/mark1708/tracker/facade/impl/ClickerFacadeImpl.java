package com.mark1708.tracker.facade.impl;

import com.mark1708.clients.tracker.dto.CreateClickerDto;
import com.mark1708.tracker.exception.http.QueryType;
import com.mark1708.tracker.exception.http.ResourceNotFoundException;
import com.mark1708.tracker.exception.http.ResourceType;
import com.mark1708.tracker.facade.ClickerFacade;
import com.mark1708.tracker.model.entities.Event;
import com.mark1708.tracker.model.entities.clicker.ClickEvent;
import com.mark1708.tracker.model.entities.clicker.ClickerInfo;
import com.mark1708.tracker.service.ClickEventService;
import com.mark1708.tracker.service.ClickerInfoService;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClickerFacadeImpl implements ClickerFacade {

  private final ClickEventService clickEventService;

  private final ClickerInfoService clickerInfoService;


  @Override
  public void processingClick(Long botId, Long userId, String slug) {
    Optional<ClickerInfo> clickerInfoOpt = clickerInfoService
        .findBySlugAndBotId(slug, botId);
    if (clickerInfoOpt.isPresent()) {
      Event event =
          Event.builder()
              .botId(botId)
              .userId(userId)
              .typeValue(1)
              .createAt(LocalDateTime.now())
              .build();

      clickEventService.save(
          new ClickEvent(event, clickerInfoOpt.get())
      );
    } else {
      throw new ResourceNotFoundException(ResourceType.CLICK_EVENT,
          QueryType.BOT_ID_AND_SLUG, botId + "/" + slug);
    }
  }

  @Override
  public ClickerInfo createClicker(Long botId, CreateClickerDto createClickerDto) {
    Optional<ClickerInfo> clickerInfoOpt = clickerInfoService
        .findBySlugAndBotId(createClickerDto.getSlug(), botId);

    if (clickerInfoOpt.isPresent()) {
      return clickerInfoOpt.get();
    } else {
      ClickerInfo clickerInfo = new ClickerInfo();
      BeanUtils.copyProperties(createClickerDto, clickerInfo);
      clickerInfo.setBotId(botId);
      clickerInfo.setUpdateAt(LocalDateTime.now());
      clickerInfo.setCreateAt(LocalDateTime.now());
      return clickerInfoService.save(clickerInfo);
    }
  }
}
