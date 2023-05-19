package com.mark1708.botfactorycore.facade;

import com.mark1708.botfactorycore.model.invitation.ApplyInvitationDto;
import com.mark1708.botfactorycore.model.invitation.CreateInvitationDto;
import com.mark1708.botfactorycore.model.invitation.InvitationDto;
import java.util.List;

public interface InvitationFacade {

  List<InvitationDto> getAllInvitations();

  InvitationDto getInvitation(Long id);

  InvitationDto createInvitation(CreateInvitationDto createInvitationDto);

  InvitationDto applyInvitation(Long id, ApplyInvitationDto applyInvitationDto);

  boolean deleteInvitation(Long id);
}
