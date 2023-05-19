package com.mark1708.botfactorycore.model.project;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProjectDto {

  Long companyId;
  String name;
  String slug;
}
