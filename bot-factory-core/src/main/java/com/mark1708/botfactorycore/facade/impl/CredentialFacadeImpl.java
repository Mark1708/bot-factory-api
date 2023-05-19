package com.mark1708.botfactorycore.facade.impl;

import com.mark1708.botfactorycore.converter.CredentialConverter;
import com.mark1708.botfactorycore.facade.CredentialFacade;
import com.mark1708.botfactorycore.model.user.CredentialDto;
import com.mark1708.botfactorycore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CredentialFacadeImpl implements CredentialFacade {

  private final UserService userService;

  private final CredentialConverter credentialConverter;

  @Override
  public CredentialDto getCredentialId(Long id) {
    return credentialConverter.toDto(
        userService.getUserById(id)
    );
  }

  @Override
  public boolean updateCredential(Long id, CredentialDto credentialDto) {
    return userService.updateCredentialData(id, credentialDto);
  }
}
