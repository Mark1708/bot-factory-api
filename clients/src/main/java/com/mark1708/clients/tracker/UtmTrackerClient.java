package com.mark1708.clients.tracker;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "event-tracker-service", path = "/api/v1/utm/{botId}")
public interface UtmTrackerClient {

  @GetMapping("/{utmId}")
  void processingUtm(
      @PathVariable(name = "botId") Long botId,
      @PathVariable(name = "utmId") String utmId,
      @RequestParam(name = "userId") Long userId,
      @RequestParam(name = "isNew", required = false, defaultValue = "false") boolean newUser
  );

  @PostMapping
  String generateLink(
      @PathVariable(name = "botId") Long botId,
      @RequestParam(name = "utm_source") String source,
      @RequestParam(name = "utm_medium") String medium,
      @RequestParam(name = "utm_campaign") String campaign,
      @RequestParam(name = "utm_content", required = false) String content,
      @RequestParam(name = "utm_term", required = false) String term
  );
}
