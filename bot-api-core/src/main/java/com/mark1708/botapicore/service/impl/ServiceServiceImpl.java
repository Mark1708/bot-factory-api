package com.mark1708.botapicore.service.impl;

import com.mark1708.botapicore.exception.http.QueryType;
import com.mark1708.botapicore.exception.http.ResourceNotFoundException;
import com.mark1708.botapicore.exception.http.ResourceType;
import com.mark1708.botapicore.model.entity.SubService;
import com.mark1708.botapicore.repository.ServiceRepository;
import com.mark1708.botapicore.service.ServiceService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {

  private final ServiceRepository repository;

  @Override
  public List<SubService> getServicesByBotId(Long botId) {
    return repository.findAllByBotId(botId);
  }

  @Override
  public List<SubService> searchServicesByBotIdAndName(Long botId, String name) {
    return repository.findAllByBotIdAndNameLike(botId, name);
  }

  @Override
  public List<SubService> searchServicesByBotIdAndType(Long botId, int type) {
    return repository.findAllByBotIdAndType(botId, type);
  }

  @Override
  public SubService getServiceByIdAndBotId(Long id, Long botId) {
    return repository.findByIdAndBotId(id, botId);
  }

  @Override
  public Optional<SubService> findServiceByNameAndBotId(String name, Long botId) {
    return repository.findByBotIdAndName(botId, name);
  }

  @Override
  public SubService saveService(SubService service) {
    return repository.saveAndFlush(service);
  }

  @Override
  public boolean deleteServiceByIdAndBotId(Long id, Long botId) {
    try {
      repository.deleteById(id);
      return true;
    } catch (Exception e) {
      throw new ResourceNotFoundException(ResourceType.SERVICE, QueryType.ID, id);
    }
  }
}
