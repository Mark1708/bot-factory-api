package com.mark1708.tracker.service.impl;

import com.mark1708.tracker.model.entities.utm.UtmLink;
import com.mark1708.tracker.repository.utm.UtmLinkRepository;
import com.mark1708.tracker.service.UtmLinkService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UtmLinkServiceImpl implements UtmLinkService {

  private final UtmLinkRepository repository;

  @Override
  public Optional<UtmLink> findByBotIdAndUtmId(Long botId, String utmId) {
    return repository.findByBotIdAndAndId(botId, utmId);
  }

  @Override
  public UtmLink save(UtmLink utmLink) {
    return repository.saveAndFlush(utmLink);
  }
}
