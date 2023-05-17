package com.mark1708.botapicore.converter;

import com.mark1708.botapicore.model.entity.Role;
import com.mark1708.botapicore.model.role.RoleDto;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {

  public RoleDto toDto(Role role) {
    RoleDto result = new RoleDto();
    BeanUtils.copyProperties(role, result);
    
    return result;
  }

  public List<RoleDto> toDto(Collection<Role> roles) {
    return roles
        .stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }
}
