package com.mark1708.botfactorycore.facade;

import com.mark1708.botfactorycore.model.entity.Role;
import com.mark1708.botfactorycore.model.role.RoleDto;
import java.util.List;

public interface RoleFacade {

  List<Role> getAllRoles();

  Role createRole(RoleDto roleDto);

  Role updateRole(Long id, RoleDto roleDto);

  boolean deleteRole(Long id);
}
