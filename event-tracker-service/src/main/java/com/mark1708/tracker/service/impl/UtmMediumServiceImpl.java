package com.mark1708.tracker.service.impl;

import com.mark1708.tracker.model.entities.utm.UtmMedium;
import com.mark1708.tracker.model.entities.utm.UtmSource;
import com.mark1708.tracker.repository.utm.UtmMediumRepository;
import com.mark1708.tracker.service.UtmMediumService;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UtmMediumServiceImpl implements UtmMediumService {

  private final UtmMediumRepository repository;

  public Optional<UtmMedium> findByName(String name) {
    return repository.findByName(name);
  }

  @Override
  public UtmMedium getOrCreate(String name) {
    return findByName(name).orElseGet(() -> {
      return repository.save(
          UtmMedium.builder()
              .name(name)
              .createAt(LocalDateTime.now())
              .build()
      );
    });
  }
}
