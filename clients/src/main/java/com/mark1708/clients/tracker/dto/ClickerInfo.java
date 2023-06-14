package com.mark1708.clients.tracker.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClickerInfo {
  private Long id;

  private String name;

  private String slug;

  private LocalDateTime createAt;

  private LocalDateTime updateAt;
}
