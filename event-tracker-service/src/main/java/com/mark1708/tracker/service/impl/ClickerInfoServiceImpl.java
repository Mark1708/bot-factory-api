package com.mark1708.tracker.service.impl;

import com.mark1708.tracker.model.entities.clicker.ClickerInfo;
import com.mark1708.tracker.repository.clicker.ClickerInfoRepository;
import com.mark1708.tracker.service.ClickerInfoService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClickerInfoServiceImpl implements ClickerInfoService {

  private final ClickerInfoRepository repository;

  @Override
  public Optional<ClickerInfo> findBySlugAndBotId(String slug, Long botId) {
    return repository.findBySlugAndBotId(slug, botId);
  }

  @Override
  public ClickerInfo save(ClickerInfo clickerInfo) {
    return repository.saveAndFlush(clickerInfo);
  }
}
