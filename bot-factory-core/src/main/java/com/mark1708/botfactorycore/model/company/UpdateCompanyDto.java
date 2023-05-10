package com.mark1708.botfactorycore.model.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCompanyDto {

  Long id;
  String name;
  String slug;
  String logoUrl;
  String bgColor;
  String textColor;
}
