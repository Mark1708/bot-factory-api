package com.mark1708.botfactorycore.service.impl;

import com.mark1708.botfactorycore.exception.http.BadRequestException;
import com.mark1708.botfactorycore.exception.http.CompanyNotFoundException;
import com.mark1708.botfactorycore.exception.http.CompanyNotFoundException.CompanySearchType;
import com.mark1708.botfactorycore.exception.http.ResourceNotFoundException;
import com.mark1708.botfactorycore.exception.http.ResourceType;
import com.mark1708.botfactorycore.model.entity.Company;
import com.mark1708.botfactorycore.repository.CompanyRepository;
import com.mark1708.botfactorycore.service.CompanyService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

  private final CompanyRepository repository;

  @Override
  public Company getCompanyById(Long id) {
    return findCompanyById(id)
        .orElseThrow(() -> new ResourceNotFoundException(ResourceType.COMPANY, id));
  }

  @Override
  public Company getCompanyBySlug(String slug) {
    return findCompanyBySlug(slug)
        .orElseThrow(() -> new CompanyNotFoundException(CompanySearchType.SLUG, slug));
  }

  @Override
  public List<Company> getAllCompanies() {
    return repository.findAll();
  }

  @Override
  public Optional<Company> findCompanyById(Long id) {
    return repository
        .findById(id);
  }

  @Override
  public Optional<Company> findCompanyBySlug(String slug) {
    return repository.findCompanyBySlug(slug);
  }

  @Override
  public Company getCompanyByUserId(Long id) {
    return repository.findCompanyByUserId(id);
  }

  @Override
  public Company getCompanyByProjectId(Long projectId) {
    return repository.findCompanyByProjectId(projectId);
  }

  @Override
  public Company saveCompany(Company company) {
    return repository.save(company);
  }

  @Override
  public boolean deleteCompanyById(Long id) {
    try {
      repository.deleteById(id);
    } catch (Exception e) {
      throw new BadRequestException(
          String.format("Company with id %d does not exist!", id)
      );
    }
    return true;
  }

  @Override
  @Transactional
  public void addUserToCompany(Long companyId, Long userId) {
    repository.addUserToCompany(companyId, userId);
  }

  @Override
  @Transactional
  public boolean deleteUserFromCompany(Long companyId, Long userId) {
    try {
      repository.deleteUserFromCompany(companyId, userId);
    } catch (Exception e) {
      throw new BadRequestException(
          String.format("Company with id %d has not user with id %d!", companyId, userId)
      );
    }
    return true;
  }
}
