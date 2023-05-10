package com.mark1708.botfactorycore.facade.impl;

import com.mark1708.botfactorycore.converter.CompanyConverter;
import com.mark1708.botfactorycore.converter.ProjectConverter;
import com.mark1708.botfactorycore.converter.UserConverter;
import com.mark1708.botfactorycore.exception.http.BadRequestException;
import com.mark1708.botfactorycore.facade.ProjectFacade;
import com.mark1708.botfactorycore.model.company.CompanyDto;
import com.mark1708.botfactorycore.model.entity.Company;
import com.mark1708.botfactorycore.model.entity.Project;
import com.mark1708.botfactorycore.model.entity.User;
import com.mark1708.botfactorycore.model.project.CreateProjectDto;
import com.mark1708.botfactorycore.model.project.ProjectDto;
import com.mark1708.botfactorycore.model.user.UserProjectDto;
import com.mark1708.botfactorycore.model.user.UserSmallDto;
import com.mark1708.botfactorycore.service.CompanyService;
import com.mark1708.botfactorycore.service.ProjectService;
import com.mark1708.botfactorycore.service.UserService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectFacadeImpl implements ProjectFacade {

  private final ProjectService projectService;
  private final CompanyService companyService;
  private final UserService userService;

  private final ProjectConverter projectConverter;
  private final CompanyConverter companyConverter;
  private final UserConverter userConverter;

  @Override
  public List<ProjectDto> getAllProjects() {
    return projectConverter.toDto(
        projectService.getAllProjects()
    );
  }

  @Override
  public ProjectDto getProject(String query, String type) {
    switch (type) {
      case "id":
        if (!NumberUtils.isDigits(query)) {
          throw new BadRequestException(
              "Query with ID type must have long type, but get - " + query);
        }

        return projectConverter.toDto(
            projectService.getProjectById(Long.valueOf(query))
        );
      case "slug":
        return projectConverter.toDto(
            projectService.getProjectBySlug(query)
        );
      default:
        throw new BadRequestException("Unrecognized type - " + type);
    }
  }

  @Override
  public ProjectDto createProject(CreateProjectDto createProjectDto) {
    projectService.findProjectBySlug(createProjectDto.getSlug())
        .ifPresent(
            s -> {
              throw new BadRequestException(
                  String.format("Project with slug - %s, already exist!",
                      createProjectDto.getSlug())
              );
            }
        );

    Company company = companyService.getCompanyById(createProjectDto.getCompanyId());

    Project project = new Project();
    BeanUtils.copyProperties(createProjectDto, project);
    project.setCompany(company);
    project.setCreatedAt(LocalDateTime.now());
    project.setBgColor("1976D2");
    project.setTextColor("ffffff");
    project.setActive(true);

    return projectConverter.toDto(
        projectService.saveProject(project)
    );
  }

  @Override
  public ProjectDto updateProject(Long id, ProjectDto projectDto) {
    Project project = projectService.getProjectById(id);

    BeanUtils.copyProperties(projectDto, project, "id");

    return projectConverter.toDto(
        projectService.saveProject(project)
    );
  }

  @Override
  public List<UserSmallDto> getProjectUsers(Long id) {
    return userConverter.toSmallDto(
        userService.getUsersByProjectId(id)
    );
  }

  @Override
  public boolean addUserToProject(Long id, UserProjectDto userProjectDto) {
    User user = userService.getUserByUsername(userProjectDto.getUsername());
    return projectService.addUserToProject(id, user.getId());
  }

  @Override
  public boolean deleteUserFromProject(Long id, UserProjectDto userProjectDto) {
    User user = userService.getUserByUsername(userProjectDto.getUsername());
    return projectService.deleteUserFromProject(id, user.getId());
  }

  @Override
  public CompanyDto getProjectCompany(Long id) {
    return companyConverter.toDto(
        companyService.getCompanyByProjectId(id)
    );
  }

  @Override
  public boolean deleteProject(Long id) {
    return projectService.deleteProjectById(id);
  }
}
