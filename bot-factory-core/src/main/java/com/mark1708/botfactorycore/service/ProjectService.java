package com.mark1708.botfactorycore.service;

import com.mark1708.botfactorycore.model.entity.Project;
import java.util.List;
import java.util.Optional;

public interface ProjectService {

  Project getProjectById(Long id);

  List<Project> getProjectsByUserId(Long id);

  List<Project> getAllProjects();

  Project getProjectBySlug(String slug);

  Optional<Project> findProjectBySlug(String slug);

  Project saveProject(Project project);

  boolean addUserToProject(Long projectId, Long userId);

  boolean deleteUserFromProject(Long projectId, Long userId);

  /**
   * Список проектов компании, в которых пользователь еще не добавлен
   *
   * @param companyId ID компании
   * @param userId    ID пользователя
   * @return Список проектов
   */
  List<Project> getCompanyProjectsAvailableForUserAdd(Long companyId, Long userId);

  List<Project> getProjectsByCompanyId(Long companyId);

  boolean deleteProjectById(Long id);
}
