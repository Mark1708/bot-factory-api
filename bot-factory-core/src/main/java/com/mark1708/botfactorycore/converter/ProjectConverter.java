package com.mark1708.botfactorycore.converter;

import com.mark1708.botfactorycore.model.entity.Project;
import com.mark1708.botfactorycore.model.project.ProjectDto;
import com.mark1708.botfactorycore.model.project.ProjectSmallDto;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProjectConverter {

  public ProjectDto toDto(Project project) {
    ProjectDto result = new ProjectDto();
    BeanUtils.copyProperties(project, result);
    result.setCompanyId(project.getCompany().getId());
    return result;
  }

  public List<ProjectDto> toDto(Collection<Project> projects) {
    return projects
        .stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }

  public ProjectSmallDto toSmallDto(Project project) {
    ProjectSmallDto result = new ProjectSmallDto();
    BeanUtils.copyProperties(project, result);
    return result;
  }

  public List<ProjectSmallDto> toSmallDto(Collection<Project> projects) {
    return projects
        .stream()
        .map(this::toSmallDto)
        .collect(Collectors.toList());
  }
}
