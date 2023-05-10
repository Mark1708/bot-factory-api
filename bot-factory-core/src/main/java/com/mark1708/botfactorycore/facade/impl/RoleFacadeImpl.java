package com.mark1708.botfactorycore.facade.impl;

import com.mark1708.botfactorycore.facade.RoleFacade;
import com.mark1708.botfactorycore.model.entity.Role;
import com.mark1708.botfactorycore.model.role.RoleDto;
import com.mark1708.botfactorycore.service.RoleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleFacadeImpl implements RoleFacade {

  private final RoleService roleService;

  @Override
  public List<Role> getAllRoles() {
    return roleService.getAllRoles();
  }

  @Override
  public Role createRole(RoleDto roleDto) {
    return roleService.createRole(roleDto);
  }

  @Override
  public Role updateRole(Long id, RoleDto roleDto) {
    return roleService.updateRole(id, roleDto);
  }

  @Override
  public boolean deleteRole(Long id) {
    return roleService.deleteRole(id);
  }
}
