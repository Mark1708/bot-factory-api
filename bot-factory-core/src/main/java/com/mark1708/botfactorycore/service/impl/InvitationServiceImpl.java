package com.mark1708.botfactorycore.service.impl;

import com.mark1708.botfactorycore.exception.http.BadRequestException;
import com.mark1708.botfactorycore.exception.http.InvitationNotFoundException;
import com.mark1708.botfactorycore.exception.http.InvitationNotFoundException.InvitationSearchType;
import com.mark1708.botfactorycore.model.entity.Invitation;
import com.mark1708.botfactorycore.repository.InvitationRepository;
import com.mark1708.botfactorycore.service.InvitationService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvitationServiceImpl implements InvitationService {

  private final InvitationRepository repository;

  @Override
  public List<Invitation> getInvitationsByCompanyId(Long companyId) {
    return repository.getInvitationsByCompanyId(companyId);
  }

  @Override
  public List<Invitation> getAllInvitations() {
    return repository.findAll();
  }

  @Override
  public Invitation getInvitation(Long id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new InvitationNotFoundException(InvitationSearchType.ID, id));
  }

  @Override
  public Invitation saveInvitation(Invitation invitation) {
    return repository.save(invitation);
  }

  @Override
  public Invitation getInvitationByEmailAndCode(String email, String code) {
    return findInvitationByEmailAndCode(email, code)
        .orElseThrow(() -> new InvitationNotFoundException(InvitationSearchType.EMAIL_CODE,
            email + ";" + code));
  }

  private Optional<Invitation> findInvitationByEmailAndCode(String email, String code) {
    return repository.findInvitationByEmailAndCode(email, code);
  }

  @Override
  public boolean deleteInvitation(Long id) {
    try {
      repository.deleteById(id);
    } catch (Exception e) {
      throw new BadRequestException(
          String.format("Invitation with id %d does not exist!", id)
      );
    }
    return true;
  }
}
