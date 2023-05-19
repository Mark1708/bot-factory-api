package com.mark1708.clients.botapicore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBotDto {

  private Long companyId;

  private String apiKey;
  private String webhookPath;

}
