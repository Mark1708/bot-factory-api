package com.mark1708.clients.tracker;

import com.mark1708.clients.tracker.dto.ClickerInfo;
import com.mark1708.clients.tracker.dto.CreateClickerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "event-tracker-service", path = "/api/v1/clicker/{botId}")
public interface ClickerTrackerClient {

  @GetMapping("/{userId}/{slug}")
  void processingClick(
      @PathVariable(name = "botId") Long botId,
      @PathVariable(name = "userId") Long userId,
      @PathVariable(name = "slug") String slug
  );

  @PostMapping
  ClickerInfo createClicker(
      @PathVariable(name = "botId") Long botId,
      @RequestBody CreateClickerDto createClickerDto
  );
}
