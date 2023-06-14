package com.mark1708.tracker.facade.impl;

import com.mark1708.tracker.exception.http.QueryType;
import com.mark1708.tracker.exception.http.ResourceNotFoundException;
import com.mark1708.tracker.exception.http.ResourceType;
import com.mark1708.tracker.facade.UtmFacade;
import com.mark1708.tracker.model.entities.Event;
import com.mark1708.tracker.model.entities.utm.UtmEvent;
import com.mark1708.tracker.model.entities.utm.UtmLink;
import com.mark1708.tracker.service.UtmCampaignService;
import com.mark1708.tracker.service.UtmContentService;
import com.mark1708.tracker.service.UtmEventService;
import com.mark1708.tracker.service.UtmLinkService;
import com.mark1708.tracker.service.UtmMediumService;
import com.mark1708.tracker.service.UtmSourceService;
import com.mark1708.tracker.service.UtmTermService;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UtmFacadeImpl implements UtmFacade {

  private final UtmEventService utmEventService;
  private final UtmLinkService linkService;

  private final UtmSourceService sourceService;
  private final UtmMediumService mediumService;
  private final UtmCampaignService campaignService;
  private final UtmContentService contentService;
  private final UtmTermService termService;

  @Override
  public String generateLink(
      Long botId, String source, String medium,
      String campaign, String content, String term
  ) {
    UtmLink utmLink = UtmLink.builder()
        .botId(botId)
        .source(sourceService.getOrCreate(source))
        .medium(mediumService.getOrCreate(medium))
        .campaign(campaignService.getOrCreate(campaign))
        .createAt(LocalDateTime.now())
        .build();

    if (content != null) {
      utmLink.setContent(contentService.getOrCreate(content));
    }

    if (term != null) {
      utmLink.setTerm(termService.getOrCreate(term));
    }

    return "utm_" + linkService.save(utmLink).getId();
  }

  @Override
  public void processingUtm(Long botId, String utmId, Long userId, boolean newUser) {
    Optional<UtmLink> utmLink = linkService.findByBotIdAndUtmId(botId, utmId);

    if (utmLink.isPresent()) {
      UtmLink link = utmLink.get();
      Event event =
          Event.builder()
              .botId(botId)
              .userId(userId)
              .typeValue(2)
              .createAt(LocalDateTime.now())
              .build();

      utmEventService.save(
          new UtmEvent(
              event, link.getSource(), link.getMedium(), link.getCampaign(),
              link.getContent(), link.getTerm(), newUser
          )
      );
    } else {
      throw new ResourceNotFoundException(ResourceType.UTM_LINK,
          QueryType.BOT_ID_AND_LINK, botId + "/" + utmId);
    }
  }
}
