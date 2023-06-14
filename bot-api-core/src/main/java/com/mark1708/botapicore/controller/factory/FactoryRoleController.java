package com.mark1708.botapicore.controller.factory;


import com.mark1708.botapicore.facade.RoleFacade;
import com.mark1708.botapicore.model.role.CreateRoleDto;
import com.mark1708.botapicore.model.role.RoleDto;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/factory/roles")
public class FactoryRoleController {

  private final RoleFacade roleFacade;

  @GetMapping("{query}")
  public RoleDto getRole(
      @RequestHeader("Api-key") String apiKey,
      @PathVariable(name = "query") String query,
      @Parameter(description = "id, name")
      @RequestParam(name = "type", defaultValue = "name") String type
  ) {
    log.debug("Get role: [{}]", query);
    return roleFacade.getRole(apiKey, query, type);
  }

  @PostMapping
  public RoleDto createRole(
      @RequestHeader("Api-key") String apiKey,
      @RequestBody CreateRoleDto createRoleDto
  ) {
    log.debug("Create role: [{}]", createRoleDto);
    return roleFacade.createRole(apiKey, createRoleDto);
  }


  @PutMapping("/{query}")
  public RoleDto updateRoleInfo(
      @RequestHeader("Api-key") String apiKey,
      @PathVariable(name = "query") String query,
      @Parameter(description = "id, name")
      @RequestParam(name = "type", defaultValue = "name") String type,
      @RequestBody RoleDto roleDto
  ) {
    log.debug("Update role info: [{}, {}]", query, roleDto);
    return roleFacade.updateRole(apiKey, query, type, roleDto);
  }

  @DeleteMapping("/{query}")
  public boolean deleteRole(
      @RequestHeader("Api-key") String apiKey,
      @PathVariable(name = "query") String query,
      @Parameter(description = "id, name")
      @RequestParam(name = "type", defaultValue = "name") String type
  ) {
    log.debug("Delete role: [{}]", query);
    return roleFacade.deleteRole(apiKey, query, type);
  }
}
