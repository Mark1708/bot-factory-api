package com.mark1708.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteNewsletter {

  private Long botId;
  private String apiKey;
  private String newsletterId;

}
