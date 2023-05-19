package com.mark1708.storageservice.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mark1708.storageservice.utils.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class FileDataDto {

  private Long id;
  private Long botId;
  private String name;
  private String slug;
  private String description;
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime createdAt;
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime updatedAt;
  private String publicLink;
}
