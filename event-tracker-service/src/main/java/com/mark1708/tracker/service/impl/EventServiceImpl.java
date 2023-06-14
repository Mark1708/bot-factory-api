package com.mark1708.tracker.service.impl;

import com.mark1708.tracker.model.entities.Event;
import com.mark1708.tracker.repository.EventRepository;
import com.mark1708.tracker.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

  private final EventRepository repository;

  @Override
  public Event save(Event event) {
    return repository.saveAndFlush(event);
  }
}
