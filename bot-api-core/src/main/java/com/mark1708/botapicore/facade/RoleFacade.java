package com.mark1708.botapicore.facade;

import com.mark1708.botapicore.model.role.CreateRoleDto;
import com.mark1708.botapicore.model.role.RoleDto;

public interface RoleFacade {

  RoleDto getRole(String apiKey, String query, String type);

  RoleDto createRole(String apiKey, CreateRoleDto createRoleDto);

  RoleDto updateRole(String apiKey, String query, String type, RoleDto roleDto);

  boolean deleteRole(String apiKey, String query, String type);
}
