package com.mark1708.tracker.service.impl;

import com.mark1708.tracker.model.entities.utm.UtmSource;
import com.mark1708.tracker.repository.utm.UtmSourceRepository;
import com.mark1708.tracker.service.UtmSourceService;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UtmSourceServiceImpl implements UtmSourceService {

  private final UtmSourceRepository repository;

  public Optional<UtmSource> findByName(String name) {
    return repository.findByName(name);
  }

  @Override
  public UtmSource getOrCreate(String name) {
    return findByName(name).orElseGet(() -> {
      return repository.save(
          UtmSource.builder()
              .name(name)
              .createAt(LocalDateTime.now())
              .build()
      );
    });
  }
}
