package com.mark1708.botfactorycore.facade;

import com.mark1708.botfactorycore.model.user.CredentialDto;

public interface CredentialFacade {

  CredentialDto getCredentialId(Long id);

  boolean updateCredential(Long id, CredentialDto credentialDto);
}
