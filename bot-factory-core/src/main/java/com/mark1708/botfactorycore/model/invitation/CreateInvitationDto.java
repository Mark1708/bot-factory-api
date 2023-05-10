package com.mark1708.botfactorycore.model.invitation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvitationDto {

  Long companyId;
  String email;

}
