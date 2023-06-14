package com.mark1708.tracker.service.impl;

import com.mark1708.tracker.model.entities.utm.UtmSource;
import com.mark1708.tracker.model.entities.utm.UtmTerm;
import com.mark1708.tracker.repository.utm.UtmTermRepository;
import com.mark1708.tracker.service.UtmTermService;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UtmTermServiceImpl implements UtmTermService {

  private UtmTermRepository repository;

  public Optional<UtmTerm> findByName(String name) {
    return repository.findByName(name);
  }

  @Override
  public UtmTerm getOrCreate(String name) {
    return findByName(name).orElseGet(() -> {
      return repository.save(
          UtmTerm.builder()
              .name(name)
              .createAt(LocalDateTime.now())
              .build()
      );
    });
  }
}
