package com.mark1708.botfactorycore.controller;

import com.mark1708.botfactorycore.facade.CompanyFacade;
import com.mark1708.botfactorycore.model.company.CompanyDto;
import com.mark1708.botfactorycore.model.company.CreateCompanyDto;
import com.mark1708.botfactorycore.model.company.TransferCompanyDto;
import com.mark1708.botfactorycore.model.company.UpdateCompanyDto;
import com.mark1708.botfactorycore.model.invitation.InvitationDto;
import com.mark1708.botfactorycore.model.project.ProjectSmallDto;
import com.mark1708.botfactorycore.model.user.UserSmallDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
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
@RequestMapping("/api/v1/companies")
@OpenAPIDefinition(
    info = @Info(
        title = "Company Controller",
        version = "1.0",
        description = "Документация к Company API"
    )
)
public class CompanyController {

  private final CompanyFacade companyFacade;

  @GetMapping("/{query}")
  public CompanyDto getCompany(@PathVariable String query,
      @RequestParam(name = "type") String type) {
    log.debug("Get company: [{}, {}]", query, type);
    return companyFacade.getCompany(query, type);
  }

  @PostMapping()
  public CompanyDto createCompany(@RequestBody CreateCompanyDto createCompanyDto) {
    log.debug("Create company: [{}]", createCompanyDto);
    return companyFacade.createCompany(createCompanyDto);
  }

  @PutMapping("/{id}")
  public CompanyDto updateCompany(@PathVariable Long id,
      @RequestBody UpdateCompanyDto updateCompanyDto) {
    log.debug("Update company info: [{}, {}]", id, updateCompanyDto);
    return companyFacade.updateCompany(id, updateCompanyDto);
  }

  @DeleteMapping("/{id}")
  public boolean deleteCompany(@PathVariable Long id) {
    log.debug("Delete company info: [{}]", id);
    return companyFacade.deleteCompany(id);
  }

  @PutMapping("/{id}/transfer")
  public CompanyDto transferCompany(
      @PathVariable Long id,
      @RequestBody TransferCompanyDto transferCompanyDto
  ) {
    log.debug("Transfer company info: [{}]", transferCompanyDto);
    return companyFacade.transferCompany(id, transferCompanyDto);
  }

  @GetMapping("/{id}/users")
  public List<UserSmallDto> getCompanyUsers(@PathVariable Long id) {
    return companyFacade.getCompanyUsers(id);
  }

  @DeleteMapping("/{id}/users/{userId}")
  public boolean deleteCompanyUser(@PathVariable Long id, @PathVariable Long userId) {
    return companyFacade.deleteCompanyUser(id, userId);
  }

  @GetMapping("/{id}/projects")
  public List<ProjectSmallDto> getCompanyProjects(@PathVariable Long id) {
    return companyFacade.getCompanyProjects(id);
  }

  @GetMapping("/{id}/invitations")
  public List<InvitationDto> getCompanyInvitations(@PathVariable Long id) {
    return companyFacade.getCompanyInvitations(id);
  }
}
