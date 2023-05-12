package com.mark1708.botapicore.model.bot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBotDto {

  private String apiKey;
  private String webhookPath;

}
