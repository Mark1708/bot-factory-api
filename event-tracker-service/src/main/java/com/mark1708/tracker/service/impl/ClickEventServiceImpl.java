package com.mark1708.tracker.service.impl;

import com.mark1708.tracker.model.entities.clicker.ClickEvent;
import com.mark1708.tracker.repository.clicker.ClickEventRepository;
import com.mark1708.tracker.service.ClickEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClickEventServiceImpl implements ClickEventService {

  private final ClickEventRepository repository;

  @Override
  public ClickEvent save(ClickEvent clickEvent) {
    return repository.saveAndFlush(clickEvent);
  }
}
