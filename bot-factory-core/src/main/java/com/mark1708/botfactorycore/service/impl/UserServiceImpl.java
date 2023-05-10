package com.mark1708.botfactorycore.service.impl;

import com.mark1708.botfactorycore.exception.http.BadRequestException;
import com.mark1708.botfactorycore.exception.http.ResourceNotFoundException;
import com.mark1708.botfactorycore.exception.http.ResourceType;
import com.mark1708.botfactorycore.exception.http.UserNotFoundException;
import com.mark1708.botfactorycore.exception.http.UserNotFoundException.UserSearchType;
import com.mark1708.botfactorycore.model.entity.Role;
import com.mark1708.botfactorycore.model.entity.User;
import com.mark1708.botfactorycore.model.user.CredentialDto;
import com.mark1708.botfactorycore.repository.UserRepository;
import com.mark1708.botfactorycore.repository.impl.OffsetBasedPageRequest;
import com.mark1708.botfactorycore.service.UserService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  @Override
  public List<User> getAll() {
    return repository.findAll();
  }

  @Override
  public List<User> getAllSearchedUsers(String search, int offset, int limit) {
    Pageable pageRequest = new OffsetBasedPageRequest(limit, offset);

    if (search != null && !search.isBlank() && !search.equals("*")) {
      return repository
          .searchUsers("%" + search + "%", pageRequest).toList();
    } else {
      return repository
          .findAll(pageRequest).toList();
    }
  }

  @Override
  public List<User> getUsersByCompanyId(Long companyId) {
    return repository.getUsersByCompanyId(companyId);
  }

  @Override
  public List<User> getUsersByProjectId(Long projectId) {
    return repository.getUsersByProjectId(projectId);
  }

  @Override
  public User getUserById(Long id) {
    return findUserById(id)
        .orElseThrow(() -> new UserNotFoundException(UserSearchType.ID, id));
  }

  @Override
  public Optional<User> findUserById(Long id) {
    return repository.findById(id);
  }

  @Override
  public User getUserByEmail(String email) {
    return findUserByEmail(email)
        .orElseThrow(() -> new UserNotFoundException(UserSearchType.EMAIL, email));
  }

  @Override
  public Optional<User> findUserByEmail(String email) {
    return repository.findByEmail(email);
  }

  @Override
  public User getUserByUsername(String username) {
    return findUserByUsername(username)
        .orElseThrow(() -> new UserNotFoundException(UserSearchType.USERNAME, username));
  }

  @Override
  public Optional<User> findUserByUsername(String username) {
    return repository.findByUsername(username);
  }

  @Override
  public Long getUsersCount() {
    return repository.count();
  }

  @Override
  public boolean updateCredentialData(Long id, CredentialDto credentialDto) {
    User user = findUserById(id)
        .orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER, id));
    user.setPassword(credentialDto.getPassword());
    user.setSalt(credentialDto.getSalt());
    log.info("{}", user);
    repository.save(user);
    return true;
  }

  @Override
  public boolean deleteUserById(Long id) {
    try {
      repository.deleteById(id);
      return true;
    } catch (Exception e) {
      throw new ResourceNotFoundException(ResourceType.USER, id);
    }
  }

  @Override
  public User saveUser(User user) {
    return repository.save(user);
  }

  @Override
  @Transactional
  public User addRoleToUser(Long id, Role role) {
    try {
      repository.addRole(id, role.getId());
    } catch (Exception e) {
      e.printStackTrace();
      throw new BadRequestException(
          String.format("User with id %d already has role with id %d!", id, role.getId())
      );
    }
    return getUserById(id);
  }

  @Override
  @Transactional
  public User deleteRoleFromUser(Long id, Role role) {
    try {
      repository.deleteRole(id, role.getId());
    } catch (Exception e) {
      throw new BadRequestException(
          String.format("User with id %d has not role with id %d!", id, role.getId())
      );
    }
    return getUserById(id);
  }
}
