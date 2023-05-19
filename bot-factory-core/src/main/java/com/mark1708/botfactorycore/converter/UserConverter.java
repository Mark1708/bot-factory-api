package com.mark1708.botfactorycore.converter;

import com.mark1708.botfactorycore.model.entity.Role;
import com.mark1708.botfactorycore.model.entity.User;
import com.mark1708.botfactorycore.model.user.UserDto;
import com.mark1708.botfactorycore.model.user.UserSmallDto;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

  public UserDto toDto(User user) {
    UserDto result = new UserDto();
    BeanUtils.copyProperties(user, result, "roles");
    result.setRoles(
        user.getRoles()
            .stream()
            .map(Role::getName)
            .collect(Collectors.toList())
    );
    return result;
  }

  public List<UserDto> toDto(Collection<User> user) {
    return user
        .stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }

  public UserSmallDto toSmallDto(User user) {
    UserSmallDto result = new UserSmallDto();
    BeanUtils.copyProperties(user, result);
    return result;
  }

  public List<UserSmallDto> toSmallDto(Collection<User> user) {
    return user
        .stream()
        .map(this::toSmallDto)
        .collect(Collectors.toList());
  }
}
