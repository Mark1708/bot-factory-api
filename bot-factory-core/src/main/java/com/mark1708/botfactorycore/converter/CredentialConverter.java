package com.mark1708.botfactorycore.converter;

import com.mark1708.botfactorycore.model.entity.User;
import com.mark1708.botfactorycore.model.user.CredentialDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CredentialConverter {

  public CredentialDto toDto(User user) {
    CredentialDto result = new CredentialDto();
    BeanUtils.copyProperties(user, result);
    return result;
  }
}
