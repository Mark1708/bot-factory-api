package com.mark1708.tracker.service.impl;

import com.mark1708.tracker.model.entities.utm.UtmContent;
import com.mark1708.tracker.model.entities.utm.UtmSource;
import com.mark1708.tracker.repository.utm.UtmContentRepository;
import com.mark1708.tracker.service.UtmContentService;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UtmContentServiceImpl implements UtmContentService {

  private final UtmContentRepository repository;

  public Optional<UtmContent> findByName(String name) {
    return repository.findByName(name);
  }

  @Override
  public UtmContent getOrCreate(String name) {
    return findByName(name).orElseGet(() -> {
      return repository.save(
          UtmContent.builder()
              .name(name)
              .createAt(LocalDateTime.now())
              .build()
      );
    });
  }
}
