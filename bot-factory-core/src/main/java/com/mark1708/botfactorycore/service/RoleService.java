package com.mark1708.botfactorycore.service;

import com.mark1708.botfactorycore.model.entity.Role;
import com.mark1708.botfactorycore.model.role.RoleDto;
import java.util.List;
import java.util.Optional;

public interface RoleService {

  Optional<Role> findRoleByName(String name);

  Role getRoleByName(String name);

  List<Role> getAllRoles();

  Role createRole(RoleDto roleDto);

  Role updateRole(Long id, RoleDto roleDto);

  boolean deleteRole(Long id);
}
