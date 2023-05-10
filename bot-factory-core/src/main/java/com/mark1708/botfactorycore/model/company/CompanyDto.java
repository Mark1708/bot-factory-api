package com.mark1708.botfactorycore.model.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {

  private Long id;
  private String name;
  private Long ownerId;
  private String slug;
  private String logoUrl;
  private String bgColor;
  private String textColor;
}
