package com.mark1708.botfactorycore.model.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCompanyDto {

  String name;
  Long ownerId;
  String slug;
}
