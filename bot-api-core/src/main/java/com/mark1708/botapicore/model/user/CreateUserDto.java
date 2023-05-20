package com.mark1708.botapicore.model.user;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {

  private String platformId;
  private String username;
  private String firstName;
  private String lastName;
  private String state;
  private Map<String, Object> additionalAttributes;
  private List<String> roles;
}
