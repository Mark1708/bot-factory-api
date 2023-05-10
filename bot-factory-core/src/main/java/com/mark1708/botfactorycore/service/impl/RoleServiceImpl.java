package com.mark1708.botfactorycore.service.impl;

import com.mark1708.botfactorycore.exception.http.BadRequestException;
import com.mark1708.botfactorycore.exception.http.ResourceNotFoundException;
import com.mark1708.botfactorycore.exception.http.ResourceType;
import com.mark1708.botfactorycore.exception.http.RoleNotFoundException;
import com.mark1708.botfactorycore.exception.http.RoleNotFoundException.RoleSearchType;
import com.mark1708.botfactorycore.model.entity.Role;
import com.mark1708.botfactorycore.model.role.RoleDto;
import com.mark1708.botfactorycore.repository.RoleRepository;
import com.mark1708.botfactorycore.service.RoleService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleRepository repository;


  private Optional<Role> findRoleById(Long id) {
    return repository.findById(id);
  }

  @Override
  public Optional<Role> findRoleByName(String name) {
    return repository.findRoleByName(name);
  }

  private Role getRoleById(Long id) {
    return findRoleById(id)
        .orElseThrow(() -> new RoleNotFoundException(RoleSearchType.ID, id));
  }

  @Override
  public Role getRoleByName(String name) {
    return findRoleByName(name)
        .orElseThrow(() -> new RoleNotFoundException(RoleSearchType.NAME, name));
  }

  @Override
  public List<Role> getAllRoles() {
    return repository.findAll();
  }

  @Override
  public Role createRole(RoleDto roleDto) {
    findRoleByName(roleDto.getName())
        .orElseThrow(() -> new BadRequestException(
            "Role with name [" + roleDto.getName() + "] already exist"
        ));
    Role role = new Role();
    role.setName(roleDto.getName());
    return repository.save(role);
  }

  @Override
  public Role updateRole(Long id, RoleDto roleDto) {
    findRoleByName(roleDto.getName())
        .orElseThrow(() -> new BadRequestException(
            "Role with name [" + roleDto.getName() + "] already exist"
        ));
    Role role = getRoleById(id);
    return repository.save(role);
  }

  @Override
  public boolean deleteRole(Long id) {
    try {
      repository.deleteById(id);
      return true;
    } catch (Exception e) {
      throw new ResourceNotFoundException(ResourceType.ROLE, id);
    }
  }
}
