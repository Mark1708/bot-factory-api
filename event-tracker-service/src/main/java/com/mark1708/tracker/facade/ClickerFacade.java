package com.mark1708.tracker.facade;

import com.mark1708.clients.tracker.dto.CreateClickerDto;
import com.mark1708.tracker.model.entities.clicker.ClickerInfo;

public interface ClickerFacade {

  void processingClick(Long botId, Long userId, String slug);

  ClickerInfo createClicker(Long botId, CreateClickerDto createClickerDto);
}
