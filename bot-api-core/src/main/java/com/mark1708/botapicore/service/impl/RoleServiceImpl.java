package com.mark1708.botapicore.service.impl;

import com.mark1708.botapicore.exception.http.QueryType;
import com.mark1708.botapicore.exception.http.ResourceNotFoundException;
import com.mark1708.botapicore.exception.http.ResourceType;
import com.mark1708.botapicore.model.entity.Role;
import com.mark1708.botapicore.repository.RoleRepository;
import com.mark1708.botapicore.service.RoleService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleRepository repository;

  @Override
  public Role getRoleById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(ResourceType.ROLE, QueryType.ID, id));
  }

  @Override
  public Role getRoleByName(Long botId, String name) {
    return findByBotIdAndName(botId, name)
        .orElseThrow(() -> new ResourceNotFoundException(ResourceType.ROLE, QueryType.NAME, name));
  }

  @Override
  public Optional<Role> findByBotIdAndName(Long botId, String name) {
    return repository.findByBotIdAndName(botId, name);
  }

  @Override
  public Role saveRole(Role role) {
    return repository.saveAndFlush(role);
  }

  @Override
  public boolean deleteById(Long id) {
    try {
      repository.deleteById(id);
      return true;
    } catch (Exception e) {
      throw new ResourceNotFoundException(ResourceType.ROLE, QueryType.ID, id);
    }
  }
}
