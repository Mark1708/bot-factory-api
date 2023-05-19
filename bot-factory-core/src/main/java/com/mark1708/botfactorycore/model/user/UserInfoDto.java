package com.mark1708.botfactorycore.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {

  private String surname;

  private String name;

  private String username;

  private String email;

}
