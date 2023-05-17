package com.mark1708.storageservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfoDto {

  private Long id;

  private String name;
  private String slug;
  private String description;
  private boolean publicLink;

}
