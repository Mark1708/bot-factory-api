package com.mark1708.botfactorycore.facade.impl;

import com.mark1708.botfactorycore.converter.CompanyConverter;
import com.mark1708.botfactorycore.converter.ProjectConverter;
import com.mark1708.botfactorycore.converter.UserConverter;
import com.mark1708.botfactorycore.exception.http.BadRequestException;
import com.mark1708.botfactorycore.facade.UserFacade;
import com.mark1708.botfactorycore.model.company.CompanyDto;
import com.mark1708.botfactorycore.model.entity.Role;
import com.mark1708.botfactorycore.model.entity.User;
import com.mark1708.botfactorycore.model.project.ProjectDto;
import com.mark1708.botfactorycore.model.role.RoleDto;
import com.mark1708.botfactorycore.model.user.UserDto;
import com.mark1708.botfactorycore.model.user.UserInfoDto;
import com.mark1708.botfactorycore.model.user.UserSmallDto;
import com.mark1708.botfactorycore.service.CompanyService;
import com.mark1708.botfactorycore.service.ProjectService;
import com.mark1708.botfactorycore.service.RoleService;
import com.mark1708.botfactorycore.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

  private final UserService userService;
  private final RoleService roleService;
  private final CompanyService companyService;
  private final ProjectService projectService;

  private final UserConverter userConverter;
  private final CompanyConverter companyConverter;
  private final ProjectConverter projectConverter;

  @Override
  public List<UserSmallDto> getAllUsers() {
    return userConverter.toSmallDto(
        userService.getAll()
    );
  }

  @Override
  public List<UserDto> getAllSearchedUsers(String search, int offset, int limit) {
    return userConverter.toDto(
        userService.getAllSearchedUsers(search, offset, limit)
    );
  }

  @Override
  public UserDto getUser(String query, String type) {
    switch (type) {
      case "id":
        if (!NumberUtils.isDigits(query)) {
          throw new BadRequestException(
              "Query with ID type must have long type, but get - " + query);
        }

        return userConverter.toDto(
            userService.getUserById(Long.valueOf(query))
        );
      case "username":
        return userConverter.toDto(
            userService.getUserByUsername(query)
        );
      case "email":
        return userConverter.toDto(
            userService.getUserByEmail(query)
        );
      default:
        throw new BadRequestException("Unrecognized type - " + type);
    }
  }

  @Override
  public UserDto updateUserInfo(Long id, UserInfoDto userInfoDto) {
    User user = userService.getUserById(id);
    BeanUtils.copyProperties(userInfoDto, user);

    return userConverter.toDto(
        userService.saveUser(user)
    );
  }

  @Override
  public boolean deleteUser(Long id) {
    return userService.deleteUserById(id);
  }

  @Override
  public UserDto addUserRole(Long id, RoleDto roleDto) {
    Role role = roleService.getRoleByName(roleDto.getName());
    return userConverter.toDto(
        userService.addRoleToUser(id, role)
    );
  }

  @Override
  public UserDto deleteUserRole(Long id, RoleDto roleDto) {
    Role role = roleService.getRoleByName(roleDto.getName());
    return userConverter.toDto(
        userService.deleteRoleFromUser(id, role)
    );
  }

  @Override
  public CompanyDto getUserCompany(Long id) {
    return companyConverter.toDto(
        companyService.getCompanyByUserId(id)
    );
  }

  @Override
  public List<ProjectDto> getUserProjects(Long id) {
    return projectConverter.toDto(
        projectService.getProjectsByUserId(id)
    );
  }

  @Override
  public Integer getUsersCount() {
    return Math.toIntExact(userService.getUsersCount());
  }
}
