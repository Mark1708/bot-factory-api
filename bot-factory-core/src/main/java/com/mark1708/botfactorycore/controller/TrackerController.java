package com.mark1708.botfactorycore.controller;

import com.mark1708.clients.tracker.EventTrackerClient;
import com.mark1708.clients.tracker.dto.ClickerInfo;
import com.mark1708.clients.tracker.dto.CreateClickerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tracker")
public class TrackerController {
  private final EventTrackerClient eventTrackerClient;

  @PostMapping("utm/{botId}")
  public String generateLink(
      @PathVariable Long botId,
      @RequestParam(name = "utm_source") String source,
      @RequestParam(name = "utm_medium") String medium,
      @RequestParam(name = "utm_campaign") String campaign,
      @RequestParam(name = "utm_content", required = false) String content,
      @RequestParam(name = "utm_term", required = false) String term
  ) {
    return eventTrackerClient.generateLink(botId, source, medium, campaign, content, term);
  }

  @PostMapping("clicker/{botId}")
  public ClickerInfo createClicker(
      @PathVariable Long botId,
      @RequestBody CreateClickerDto createClickerDto
  ) {
    return eventTrackerClient.createClicker(botId, createClickerDto);
  }
}
