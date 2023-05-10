package com.mark1708.botfactorycore.admin;

import com.mark1708.botfactorycore.facade.CompanyFacade;
import com.mark1708.botfactorycore.model.company.CompanyDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/companies")
public class AdminCompanyController {

  private final CompanyFacade companyFacade;

  @GetMapping
  public List<CompanyDto> getAllCompanies() {
    log.debug("Get all companies");
    return companyFacade.getAllCompanies();
  }
}
