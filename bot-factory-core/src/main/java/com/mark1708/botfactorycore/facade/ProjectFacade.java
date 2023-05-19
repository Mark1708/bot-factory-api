package com.mark1708.botfactorycore.facade;

import com.mark1708.botfactorycore.model.company.CompanyDto;
import com.mark1708.botfactorycore.model.project.CreateProjectDto;
import com.mark1708.botfactorycore.model.project.ProjectDto;
import com.mark1708.botfactorycore.model.user.UserProjectDto;
import com.mark1708.botfactorycore.model.user.UserSmallDto;
import java.util.List;

public interface ProjectFacade {

  List<ProjectDto> getAllProjects();

  ProjectDto getProject(String query, String type);

  ProjectDto createProject(CreateProjectDto createProjectDto);

  boolean isExistByApiKey(String apiKey);

  ProjectDto updateProject(Long id, ProjectDto project);

  List<UserSmallDto> getProjectUsers(Long id);

  boolean addUserToProject(Long id, UserProjectDto userProjectDto);

  boolean deleteUserFromProject(Long id, UserProjectDto userProjectDto);

  CompanyDto getProjectCompany(Long id);

  boolean deleteProject(Long id);
}
