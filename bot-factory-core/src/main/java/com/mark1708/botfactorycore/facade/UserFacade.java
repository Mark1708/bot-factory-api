package com.mark1708.botfactorycore.facade;

import com.mark1708.botfactorycore.model.company.CompanyDto;
import com.mark1708.botfactorycore.model.project.ProjectDto;
import com.mark1708.botfactorycore.model.role.RoleDto;
import com.mark1708.botfactorycore.model.user.UserDto;
import com.mark1708.botfactorycore.model.user.UserInfoDto;
import com.mark1708.botfactorycore.model.user.UserSmallDto;
import java.util.List;

public interface UserFacade {

  List<UserSmallDto> getAllUsers();

  List<UserDto> getAllSearchedUsers(String search, int offset, int limit);

  UserDto getUser(String query, String type);

  UserDto updateUserInfo(Long id, UserInfoDto userInfoDto);

  boolean deleteUser(Long id);

  UserDto addUserRole(Long id, RoleDto roleDto);

  UserDto deleteUserRole(Long id, RoleDto roleDto);

  CompanyDto getUserCompany(Long id);

  List<ProjectDto> getUserProjects(Long id);

  Integer getUsersCount();
}
