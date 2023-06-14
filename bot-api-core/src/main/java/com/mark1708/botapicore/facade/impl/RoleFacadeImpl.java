package com.mark1708.botapicore.facade.impl;

import com.mark1708.botapicore.converter.RoleConverter;
import com.mark1708.botapicore.exception.http.BadRequestException;
import com.mark1708.botapicore.facade.RoleFacade;
import com.mark1708.botapicore.model.entity.Bot;
import com.mark1708.botapicore.model.entity.Role;
import com.mark1708.botapicore.model.role.CreateRoleDto;
import com.mark1708.botapicore.model.role.RoleDto;
import com.mark1708.botapicore.service.BotService;
import com.mark1708.botapicore.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleFacadeImpl implements RoleFacade {

  private final RoleService roleService;
  private final BotService botService;

  private final RoleConverter roleConverter;

  @Override
  public RoleDto getRole(String apiKey, String query, String type) {
    Bot bot = botService.getBotByApiKey(apiKey);
    switch (type) {
      case "id":
        if (!NumberUtils.isDigits(query)) {
          throw new BadRequestException(
              "Query with ID type must have long type, but get - " + query);
        }
        return roleConverter.toDto(
          roleService.getRoleById(Long.valueOf(query))
        );
      case "name":
        return roleConverter.toDto(
            roleService.getRoleByName(bot.getId(), query)
        );
      default:
        throw new BadRequestException("Unrecognized type - " + type);
    }

  }

  @Override
  public RoleDto createRole(String apiKey, CreateRoleDto createRoleDto) {
    Bot bot = botService.getBotByApiKey(apiKey);
    String name = createRoleDto.getName();
    roleService.findByBotIdAndName(bot.getId(), name)
        .ifPresent(s -> {
              throw new BadRequestException(
                  String.format("Role with name - %s, already exist!", name)
              );
            }
        );

    Role role = Role.builder()
        .bot(bot)
        .name(name)
        .build();

    return roleConverter.toDto(
      roleService.saveRole(role)
    );
  }

  @Override
  public RoleDto updateRole(String apiKey, String query, String type, RoleDto roleDto) {
    Bot bot = botService.getBotByApiKey(apiKey);
    Role role;
    switch (type) {
      case "id":
        if (!NumberUtils.isDigits(query)) {
          throw new BadRequestException(
              "Query with ID type must have long type, but get - " + query);
        }
        role = roleService.getRoleById(Long.valueOf(query));
        break;
      case "name":
        role = roleService.getRoleByName(bot.getId(), query);
        break;
      default:
        throw new BadRequestException("Unrecognized type - " + type);
    }

    role.setName(role.getName());

    return roleConverter.toDto(
        roleService.saveRole(role)
    );
  }

  @Override
  public boolean deleteRole(String apiKey, String query, String type) {
    Bot bot = botService.getBotByApiKey(apiKey);
    Role role;
    switch (type) {
      case "id":
        if (!NumberUtils.isDigits(query)) {
          throw new BadRequestException(
              "Query with ID type must have long type, but get - " + query);
        }
        role = roleService.getRoleById(Long.valueOf(query));
        break;
      case "name":
        role = roleService.getRoleByName(bot.getId(), query);
        break;
      default:
        throw new BadRequestException("Unrecognized type - " + type);
    }
    return roleService.deleteById(role.getId());
  }
}
