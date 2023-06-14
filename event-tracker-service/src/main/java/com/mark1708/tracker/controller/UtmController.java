package com.mark1708.tracker.controller;

import com.mark1708.tracker.facade.UtmFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/utm/{botId}")
public class UtmController {

  private final UtmFacade utmFacade;

  @GetMapping("/{utmId}")
  public void processingUtm(
      @PathVariable Long botId,
      @PathVariable String utmId,
      @RequestParam(name = "userId") Long userId,
      @RequestParam(name = "isNew", required = false, defaultValue = "false") boolean newUser
  ) {
    utmFacade.processingUtm(botId, utmId, userId, newUser);
  }

  @PostMapping
  public String generateLink(
      @PathVariable Long botId,
      @RequestParam(name = "utm_source") String source,
      @RequestParam(name = "utm_medium") String medium,
      @RequestParam(name = "utm_campaign") String campaign,
      @RequestParam(name = "utm_content", required = false) String content,
      @RequestParam(name = "utm_term", required = false) String term
  ) {
    return utmFacade.generateLink(botId, source, medium, campaign, content, term);
  }


}
