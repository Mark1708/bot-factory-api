package com.mark1708.botfactorycore.facade;

import com.mark1708.botfactorycore.model.company.CompanyDto;
import com.mark1708.botfactorycore.model.company.CreateCompanyDto;
import com.mark1708.botfactorycore.model.company.TransferCompanyDto;
import com.mark1708.botfactorycore.model.company.UpdateCompanyDto;
import com.mark1708.botfactorycore.model.invitation.InvitationDto;
import com.mark1708.botfactorycore.model.project.ProjectSmallDto;
import com.mark1708.botfactorycore.model.user.UserSmallDto;
import java.util.List;

public interface CompanyFacade {

  List<CompanyDto> getAllCompanies();

  CompanyDto getCompany(String query, String type);

  CompanyDto createCompany(CreateCompanyDto createCompanyDto);

  CompanyDto updateCompany(Long id, UpdateCompanyDto updateCompanyDto);

  CompanyDto transferCompany(Long id, TransferCompanyDto transferCompanyDto);

  List<UserSmallDto> getCompanyUsers(Long id);

  List<ProjectSmallDto> getCompanyProjects(Long id);

  List<InvitationDto> getCompanyInvitations(Long id);

  boolean deleteCompany(Long id);

  boolean deleteCompanyUser(Long id, Long userId);
}
