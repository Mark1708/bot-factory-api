package com.mark1708.botfactorycore.model.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectSmallDto {

  private Long id;
  private Long botId;
  private String name;
  private String description;
  private String slug;
  private String logoUrl;
  private String bgColor;
  private String textColor;
  private boolean active;
}
