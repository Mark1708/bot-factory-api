package com.mark1708.botfactorycore.service;

import com.mark1708.botfactorycore.model.entity.Invitation;
import java.util.List;

public interface InvitationService {

  List<Invitation> getInvitationsByCompanyId(Long companyId);

  List<Invitation> getAllInvitations();

  Invitation getInvitation(Long id);

  Invitation saveInvitation(Invitation invitation);

  Invitation getInvitationByEmailAndCode(String email, String code);

  boolean deleteInvitation(Long id);
}
