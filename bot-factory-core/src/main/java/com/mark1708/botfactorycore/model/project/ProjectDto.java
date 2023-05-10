package com.mark1708.botfactorycore.model.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {

  private Long id;
  private Long botId;
  private Long companyId;
  private String name;
  private String description;
  private String slug;
  private String logoUrl;
  private String bgColor;
  private String textColor;
  private String apiKey;
  private String webhookPath;
  private boolean active;
}
