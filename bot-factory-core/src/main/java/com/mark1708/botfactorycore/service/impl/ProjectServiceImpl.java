package com.mark1708.botfactorycore.service.impl;

import com.mark1708.botfactorycore.exception.http.BadRequestException;
import com.mark1708.botfactorycore.exception.http.ProjectNotFoundException;
import com.mark1708.botfactorycore.exception.http.ProjectNotFoundException.ProjectSearchType;
import com.mark1708.botfactorycore.model.entity.Project;
import com.mark1708.botfactorycore.repository.ProjectRepository;
import com.mark1708.botfactorycore.service.ProjectService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

  private final ProjectRepository repository;

  @Override
  public List<Project> getAllProjects() {
    return repository.findAll();
  }

  @Override
  public List<Project> getCompanyProjectsAvailableForUserAdd(Long companyId, Long userId) {
    return repository.getCompanyProjectsAvailableForUserAdd(companyId, userId);
  }

  @Override
  public List<Project> getProjectsByCompanyId(Long companyId) {
    return repository.getCompanyProjectsById(companyId);
  }

  @Override
  public List<Project> getProjectsByUserId(Long id) {
    return repository.findProjectsByUserId(id);
  }

  @Override
  public Optional<Project> findProjectBySlug(String slug) {
    return repository.findProjectBySlug(slug);
  }

  @Override
  public Project getProjectBySlug(String slug) {
    return findProjectBySlug(slug)
        .orElseThrow(() -> new ProjectNotFoundException(ProjectSearchType.SLUG, slug));
  }

  @Override
  public Project getProjectById(Long id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new ProjectNotFoundException(ProjectSearchType.ID, id));
  }

  @Override
  public Project saveProject(Project project) {
    return repository.save(project);
  }

  @Override
  @Transactional
  public boolean addUserToProject(Long projectId, Long userId) {
    try {
      repository.addUserToProject(projectId, userId);
    } catch (Exception e) {
      throw new BadRequestException(
          String.format("Project with id %d already has user with id %d!", projectId, userId)
      );
    }
    return true;
  }

  @Override
  @Transactional
  public boolean deleteUserFromProject(Long projectId, Long userId) {
    try {
      repository.deleteUserFromProject(projectId, userId);
    } catch (Exception e) {
      throw new BadRequestException(
          String.format("Project with id %d has not user with id %d!", projectId, userId)
      );
    }
    return true;
  }

  @Override
  public boolean deleteProjectById(Long id) {
    try {
      repository.deleteById(id);
    } catch (Exception e) {
      throw new BadRequestException(
          String.format("Project with id %d does not exist!", id)
      );
    }
    return true;
  }

  @Override
  public boolean isExistByApiKey(String apiKey) {
    return repository.existsByApiKey(apiKey);
  }
}
