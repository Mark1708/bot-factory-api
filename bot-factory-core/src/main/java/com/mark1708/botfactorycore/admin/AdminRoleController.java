package com.mark1708.botfactorycore.admin;

import com.mark1708.botfactorycore.facade.RoleFacade;
import com.mark1708.botfactorycore.model.entity.Role;
import com.mark1708.botfactorycore.model.role.RoleDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/roles")
public class AdminRoleController {

  private final RoleFacade roleFacade;

  @GetMapping
  public List<Role> getAllRoles() {
    log.debug("Get all roles");
    return roleFacade.getAllRoles();
  }

  @PostMapping
  public Role createRole(@RequestBody RoleDto roleDto) {
    log.debug("Create role: [{}]", roleDto);
    return roleFacade.createRole(roleDto);
  }

  @PutMapping("/{id}")
  public Role updateRole(@PathVariable Long id, @RequestBody RoleDto roleDto) {
    log.debug("Update role: [{}, {}]", id, roleDto);
    return roleFacade.updateRole(id, roleDto);
  }

  @DeleteMapping("/{id}")
  public boolean deleteRole(@PathVariable Long id) {
    log.debug("Delete role: [{}]", id);
    return roleFacade.deleteRole(id);
  }
}
