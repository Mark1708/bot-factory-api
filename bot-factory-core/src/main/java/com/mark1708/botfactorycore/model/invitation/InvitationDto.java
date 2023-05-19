package com.mark1708.botfactorycore.model.invitation;


import java.time.LocalDateTime;
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
  private LocalDateTime sendAt;
  private String email;
  private boolean confirm;
}
