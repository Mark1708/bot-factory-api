package com.mark1708.tracker.service.impl;

import com.mark1708.tracker.model.entities.utm.UtmCampaign;
import com.mark1708.tracker.model.entities.utm.UtmSource;
import com.mark1708.tracker.repository.utm.UtmCampaignRepository;
import com.mark1708.tracker.service.UtmCampaignService;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UtmCampaignServiceImpl implements UtmCampaignService {

  private final UtmCampaignRepository repository;

  public Optional<UtmCampaign> findByName(String name) {
    return repository.findByName(name);
  }

  @Override
  public UtmCampaign getOrCreate(String name) {
    return findByName(name).orElseGet(() -> {
      return repository.save(
          UtmCampaign.builder()
              .name(name)
              .createAt(LocalDateTime.now())
              .build()
      );
    });
  }
}
