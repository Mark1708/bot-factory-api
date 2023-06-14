package com.mark1708.clients.tracker;

import com.mark1708.clients.tracker.dto.ClickerInfo;
import com.mark1708.clients.tracker.dto.CreateClickerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "event-tracker-service", path = "/api/v1")
public interface EventTrackerClient {

  @GetMapping("/clicker/{botId}/{userId}/{slug}")
  void processingClick(
      @PathVariable(name = "botId") Long botId,
      @PathVariable(name = "userId") Long userId,
      @PathVariable(name = "slug") String slug
  );

  @PostMapping("/clicker/{botId}")
  ClickerInfo createClicker(
      @PathVariable(name = "botId") Long botId,
      @RequestBody CreateClickerDto createClickerDto
  );

  @GetMapping("/utm/{botId}/{utmId}")
  void processingUtm(
      @PathVariable(name = "botId") Long botId,
      @PathVariable(name = "utmId") String utmId,
      @RequestParam(name = "userId") Long userId,
      @RequestParam(name = "isNew", required = false, defaultValue = "false") boolean newUser
  );

  @PostMapping("/utm/{botId}")
  String generateLink(
      @PathVariable(name = "botId") Long botId,
      @RequestParam(name = "utm_source") String source,
      @RequestParam(name = "utm_medium") String medium,
      @RequestParam(name = "utm_campaign") String campaign,
      @RequestParam(name = "utm_content", required = false) String content,
      @RequestParam(name = "utm_term", required = false) String term
  );
}
