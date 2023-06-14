package com.mark1708.tracker.controller;

import com.mark1708.clients.tracker.dto.CreateClickerDto;
import com.mark1708.tracker.facade.ClickerFacade;
import com.mark1708.tracker.model.entities.clicker.ClickerInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clicker/{botId}")
public class ClickerController {

  private final ClickerFacade clickerFacade;

  @GetMapping("/{userId}/{slug}")
  public void processingClick(
      @PathVariable Long botId,
      @PathVariable Long userId,
      @PathVariable String slug
  ) {
    clickerFacade.processingClick(botId, userId, slug);
  }

  @PostMapping
  public ClickerInfo createClicker(
      @PathVariable Long botId,
      @RequestBody CreateClickerDto createClickerDto
  ) {
    return clickerFacade.createClicker(botId, createClickerDto);
  }
}
