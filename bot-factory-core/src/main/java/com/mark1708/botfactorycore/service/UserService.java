package com.mark1708.botfactorycore.service;

import com.mark1708.botfactorycore.model.entity.Role;
import com.mark1708.botfactorycore.model.entity.User;
import com.mark1708.botfactorycore.model.user.CredentialDto;
import java.util.List;
import java.util.Optional;

public interface UserService {

  List<User> getAll();

  List<User> getAllSearchedUsers(String search, int offset, int limit);

  List<User> getUsersByProjectId(Long projectId);

  User getUserById(Long id);

  Optional<User> findUserById(Long id);

  User getUserByEmail(String email);

  Optional<User> findUserByEmail(String email);

  User getUserByUsername(String username);

  Optional<User> findUserByUsername(String username);

  Long getUsersCount();

  boolean updateCredentialData(Long id, CredentialDto credentialDto);

  boolean deleteUserById(Long id);

  User saveUser(User user);

  User addRoleToUser(Long id, Role role);

  User deleteRoleFromUser(Long id, Role role);

  List<User> getUsersByCompanyId(Long companyId);
}
