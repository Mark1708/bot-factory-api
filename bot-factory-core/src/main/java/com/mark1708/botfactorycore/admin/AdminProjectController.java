package com.mark1708.botfactorycore.admin;

import com.mark1708.botfactorycore.facade.ProjectFacade;
import com.mark1708.botfactorycore.model.project.ProjectDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/projects")
public class AdminProjectController {

  private final ProjectFacade projectFacade;

  @GetMapping
  public List<ProjectDto> getAllProjects() {
    log.debug("Get all projects");
    return projectFacade.getAllProjects();
  }
}
