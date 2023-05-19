package com.mark1708.botfactorycore.service;

import com.mark1708.botfactorycore.model.entity.Company;
import java.util.List;
import java.util.Optional;

public interface CompanyService {

  List<Company> getAllCompanies();

  Optional<Company> findCompanyById(Long id);

  Optional<Company> findCompanyBySlug(String slug);

  Company getCompanyById(Long id);

  Company getCompanyBySlug(String slug);

  Company getCompanyByUserId(Long id);

  Company getCompanyByProjectId(Long projectId);

  Company saveCompany(Company company);

  boolean deleteCompanyById(Long id);

  void addUserToCompany(Long companyId, Long userId);

  boolean deleteUserFromCompany(Long companyId, Long userId);
}
