package com.mark1708.botapicore.converter;

import com.mark1708.botapicore.model.entity.User;
import com.mark1708.botapicore.model.user.UserDto;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

  public UserDto toDto(User user) {
    UserDto result = new UserDto();
    BeanUtils.copyProperties(user, result);


    return result;
  }

  public List<UserDto> toDto(Collection<User> users) {
    return users
        .stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }
}
