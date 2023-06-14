package com.mark1708.tracker.service.impl;

import com.mark1708.tracker.model.entities.utm.UtmEvent;
import com.mark1708.tracker.repository.utm.UtmEventRepository;
import com.mark1708.tracker.service.UtmEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UtmEventServiceImpl implements UtmEventService {

  private final UtmEventRepository repository;

  @Override
  public UtmEvent save(UtmEvent event) {
    return repository.saveAndFlush(event);
  }
}
