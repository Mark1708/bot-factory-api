package com.mark1708.botapicore.model.user;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mark1708.botapicore.utils.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  private Long id;
  private String platformId;
  private String username;
  private String firstName;
  private String lastName;
  private boolean blocked;
  private String state;

  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime registeredAt;

  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime lastActivityAt;

  @JsonAnyGetter
  private Map<String, Object> additionalAttributes;
  private List<String> roles;

  @JsonAnySetter
  public void add(String key, Object value) {
    additionalAttributes.put(key, value);
  }
}
