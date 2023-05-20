package com.mark1708.botapicore.service.impl;

import com.mark1708.botapicore.exception.http.QueryType;
import com.mark1708.botapicore.exception.http.ResourceNotFoundException;
import com.mark1708.botapicore.exception.http.ResourceType;
import com.mark1708.botapicore.model.entity.Tariff;
import com.mark1708.botapicore.repository.TariffRepository;
import com.mark1708.botapicore.service.TariffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TariffServiceImpl implements TariffService {

  private final TariffRepository repository;

  @Override
  public Tariff getTariffById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(ResourceType.TARIFF, QueryType.ID, id));
  }

  @Override
  public Tariff saveTariff(Tariff tariff) {
    return repository.saveAndFlush(tariff);
  }

  @Override
  public void deleteTariffByIdAndServiceId(Long id, Long serviceId) {
      repository.deleteById(id);
  }

  @Override
  public boolean deleteTariffById(Long id) {
    try {
      repository.deleteById(id);
      return true;
    } catch (Exception e) {
      throw new ResourceNotFoundException(ResourceType.TARIFF, QueryType.ID, id);
    }
  }
}
