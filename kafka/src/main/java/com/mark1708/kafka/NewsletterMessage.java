package com.mark1708.kafka;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsletterMessage {

  private Long botId;
  private String apiKey;
  private List<String> chatIds;
  private String text;
  private List<Document> documents;

}
