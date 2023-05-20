package com.mark1708.botapicore.service.impl;

import com.mark1708.botapicore.exception.http.QueryType;
import com.mark1708.botapicore.exception.http.ResourceNotFoundException;
import com.mark1708.botapicore.exception.http.ResourceType;
import com.mark1708.botapicore.model.entity.User;
import com.mark1708.botapicore.repository.UserRepository;
import com.mark1708.botapicore.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  @Override
  public List<User> getUsersByBotId(Long botId) {
    return repository.findAllByBotId(botId);
  }

  @Override
  public User getUserById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER, QueryType.ID, id));
  }

  @Override
  public List<User> searchUsersByBotIdAndUsername(Long botId, String username) {
    return repository.findAllByUsernameLikeAndBotId(username, botId);
  }

  @Override
  public List<User> searchUsersByBotIdAndFirstName(Long botId, String firstName) {
    return repository.searchByFullNameByBotId(firstName, botId);
  }

  @Override
  public List<User> searchUsersByBotIdAndLastName(Long botId, String lastName) {
    return repository.searchByFullNameByBotId(lastName, botId);
  }

  @Override
  public List<User> searchUsersByBotIdAndState(Long botId, String state) {
    return repository.findAllByBotIdAndState(botId, state);
  }

  @Override
  public User getUserByIdAndBotId(Long id, Long botId) {
    return repository.findByIdAndBotId(id, botId)
        .orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER, QueryType.ID, id));
  }

  @Override
  public User getUserByPlatformIdAndBotId(String platformId, Long botId) {
    return repository.findByPlatformIdAndBotId(platformId, botId)
        .orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER, QueryType.PLATFORM_ID, platformId));
  }

  @Override
  public User saveUser(User user) {
    return repository.saveAndFlush(user);
  }

  @Override
  public void addRoleToUser(Long id, Long roleId) {
    repository.addRole(id, roleId);
  }

  @Override
  public void deleteRoleFromUser(Long id, Long roleId) {
    repository.deleteRole(id, roleId);
  }

  @Override
  public boolean existByBotIdAndPlatformId(Long botId, String platformId) {
    return repository.existsByBotIdAndPlatformId(botId, platformId);
  }

  @Override
  public boolean deleteUserById(Long id) {
    try {
      repository.deleteById(id);
      return true;
    } catch (Exception e) {
      throw new ResourceNotFoundException(ResourceType.USER, QueryType.ID, id);
    }
  }
}
