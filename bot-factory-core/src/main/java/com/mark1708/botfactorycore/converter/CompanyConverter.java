package com.mark1708.botfactorycore.converter;

import com.mark1708.botfactorycore.model.company.CompanyDto;
import com.mark1708.botfactorycore.model.entity.Company;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CompanyConverter {

  public CompanyDto toDto(Company company) {
    CompanyDto result = new CompanyDto();
    BeanUtils.copyProperties(company, result);
    return result;
  }

  public List<CompanyDto> toDto(Collection<Company> companies) {
    return companies
        .stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }
}
