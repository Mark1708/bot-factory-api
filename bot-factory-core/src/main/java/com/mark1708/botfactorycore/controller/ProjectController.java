package com.mark1708.botfactorycore.controller;

import com.mark1708.botfactorycore.facade.ProjectFacade;
import com.mark1708.botfactorycore.model.company.CompanyDto;
import com.mark1708.botfactorycore.model.project.CreateProjectDto;
import com.mark1708.botfactorycore.model.project.ProjectDto;
import com.mark1708.botfactorycore.model.user.UserProjectDto;
import com.mark1708.botfactorycore.model.user.UserSmallDto;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/factory/projects")
public class ProjectController {

  private final ProjectFacade projectFacade;

  @GetMapping("/{query}")
  public ProjectDto getProject(@PathVariable String query,
      @RequestParam(name = "type") String type) {
    log.debug("Get project: [{}, {}]", query, type);
    return projectFacade.getProject(query, type);
  }

  @PostMapping
  public ProjectDto createProject(@RequestBody CreateProjectDto createProjectDto) {
    log.debug("Create project: [{}]", createProjectDto);
    return projectFacade.createProject(createProjectDto);
  }

  @PutMapping("/{id}")
  public ProjectDto updateProject(@PathVariable Long id, @RequestBody ProjectDto projectDto) {
    log.debug("Update project: [{}, {}]", id, projectDto);
    return projectFacade.updateProject(id, projectDto);
  }

  @DeleteMapping("/{id}")
  public boolean deleteProject(@PathVariable Long id) {
    log.debug("Delete project: [{}, {}]", id);
    return projectFacade.deleteProject(id);
  }

  @GetMapping("/{id}/users")
  public List<UserSmallDto> getProjectUsers(@PathVariable Long id) {
    log.debug("Get project's users: [{}]", id);
    return projectFacade.getProjectUsers(id);
  }

  @GetMapping("/{id}/company")
  public CompanyDto getProjectCompany(@PathVariable Long id) {
    log.debug("Get project's company: [{}]", id);
    return projectFacade.getProjectCompany(id);
  }

  @PostMapping("/{id}/users")
  public boolean addUserToProject(
      @PathVariable Long id,
      @RequestBody UserProjectDto userProjectDto
  ) {
    log.debug("Add user to project: [{}, {}]", id, userProjectDto);
    return projectFacade.addUserToProject(id, userProjectDto);
  }

  @DeleteMapping("/{id}/users")
  public boolean deleteUserFromProject(
      @PathVariable Long id,
      @RequestBody UserProjectDto userProjectDto
  ) {
    log.debug("Delete user from project: [{}, {}]", id, userProjectDto);
    return projectFacade.deleteUserFromProject(id, userProjectDto);
  }
}
