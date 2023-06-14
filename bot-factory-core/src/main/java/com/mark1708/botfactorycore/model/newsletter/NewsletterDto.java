package com.mark1708.botfactorycore.model.newsletter;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsletterDto {

  private List<String> chatIds;
  private String text;
}
