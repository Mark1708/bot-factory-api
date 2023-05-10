package com.mark1708.botfactorycore.model.invitation;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvitationDto {

  private Long id;
  private String email;
  private boolean confirm;
}
