package com.mark1708.botfactorycore.facade.impl;

import com.mark1708.botfactorycore.converter.CompanyConverter;
import com.mark1708.botfactorycore.converter.InvitationConverter;
import com.mark1708.botfactorycore.converter.ProjectConverter;
import com.mark1708.botfactorycore.converter.UserConverter;
import com.mark1708.botfactorycore.exception.http.BadRequestException;
import com.mark1708.botfactorycore.exception.http.UserNotFoundException;
import com.mark1708.botfactorycore.exception.http.UserNotFoundException.UserSearchType;
import com.mark1708.botfactorycore.facade.CompanyFacade;
import com.mark1708.botfactorycore.model.company.CompanyDto;
import com.mark1708.botfactorycore.model.company.CreateCompanyDto;
import com.mark1708.botfactorycore.model.company.TransferCompanyDto;
import com.mark1708.botfactorycore.model.company.UpdateCompanyDto;
import com.mark1708.botfactorycore.model.entity.Company;
import com.mark1708.botfactorycore.model.entity.User;
import com.mark1708.botfactorycore.model.invitation.InvitationDto;
import com.mark1708.botfactorycore.model.project.ProjectSmallDto;
import com.mark1708.botfactorycore.model.user.UserSmallDto;
import com.mark1708.botfactorycore.service.CompanyService;
import com.mark1708.botfactorycore.service.InvitationService;
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
public class CompanyFacadeImplImpl implements CompanyFacade {

  private final InvitationService invitationService;
  private final ProjectService projectService;
  private final CompanyService companyService;
  private final UserService userService;

  private final InvitationConverter invitationConverter;
  private final ProjectConverter projectConverter;
  private final CompanyConverter companyConverter;
  private final UserConverter userConverter;

  @Override
  public List<CompanyDto> getAllCompanies() {
    return companyConverter.toDto(
        companyService.getAllCompanies()
    );
  }

  @Override
  public CompanyDto getCompany(String query, String type) {
    switch (type) {
      case "id":
        if (!NumberUtils.isDigits(query)) {
          throw new BadRequestException(
              "Query with ID type must have long type, but get - " + query);
        }

        return companyConverter.toDto(
            companyService.getCompanyById(Long.valueOf(query))
        );
      case "slug":
        return companyConverter.toDto(
            companyService.getCompanyBySlug(query)
        );
      default:
        throw new BadRequestException("Unrecognized type - " + type);
    }
  }

  @Override
  public CompanyDto createCompany(CreateCompanyDto createCompanyDto) {
    companyService.findCompanyBySlug(createCompanyDto.getSlug())
        .orElseThrow(() -> new BadRequestException(
            String.format("Company with slug - %s, already exist!", createCompanyDto.getSlug())
        ));

    userService.findUserById(createCompanyDto.getOwnerId())
        .orElseThrow(() ->
            new UserNotFoundException(UserSearchType.ID, createCompanyDto.getOwnerId())
        );

    Company company = new Company();
    BeanUtils.copyProperties(createCompanyDto, company);
    company.setCreatedAt(LocalDateTime.now());
    company.setBgColor("1976D2");
    company.setTextColor("ffffff");

    return companyConverter.toDto(
        companyService.saveCompany(company)
    );
  }

  @Override
  public CompanyDto updateCompany(Long id, UpdateCompanyDto updateCompanyDto) {
    Company company = companyService.getCompanyById(id);

    BeanUtils.copyProperties(updateCompanyDto, company);
    return companyConverter.toDto(
        companyService.saveCompany(company)
    );
  }

  @Override
  public CompanyDto transferCompany(Long id, TransferCompanyDto transferCompanyDto) {
    User newOwner = userService.getUserByUsername(transferCompanyDto.getNewOwnerUsername());
    Company company = companyService.getCompanyById(id);
    company.setOwnerId(newOwner.getId());

    // TODO: проверить, что пользователь является сотрудником компании и поменять ему роль

    projectService.getCompanyProjectsAvailableForUserAdd(id, newOwner.getId())
        .forEach(
            project -> {
              projectService.addUserToProject(project.getId(), newOwner.getId());
            }
        );

    return companyConverter.toDto(
        companyService.saveCompany(company)
    );
  }

  @Override
  public List<UserSmallDto> getCompanyUsers(Long companyId) {
    return userConverter.toSmallDto(
        userService.getUsersByCompanyId(companyId)
    );
  }

  @Override
  public List<ProjectSmallDto> getCompanyProjects(Long id) {
    return projectConverter.toSmallDto(
        projectService.getProjectsByCompanyId(id)
    );
  }

  @Override
  public List<InvitationDto> getCompanyInvitations(Long id) {
    return invitationConverter.toDto(
        invitationService.getInvitationsByCompanyId(id)
    );
  }

  @Override
  public boolean deleteCompany(Long id) {
    return companyService.deleteCompanyById(id);
  }

  @Override
  public boolean deleteCompanyUser(Long id, Long userId) {
    return companyService.deleteUserFromCompany(id, userId);
  }
}
