package com.mark1708.botapicore.service;

import com.mark1708.botapicore.model.entity.Role;
import java.util.Optional;

public interface RoleService {

  Role getRoleById(Long id);

  Role getRoleByName(Long botId, String name);

  Role saveRole(Role role);

  boolean deleteById(Long id);

  Optional<Role> findByBotIdAndName(Long botId, String name);
}
