package com.mark1708.botfactorycore.admin;

import com.mark1708.botfactorycore.facade.InvitationFacade;
import com.mark1708.botfactorycore.model.invitation.InvitationDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/invitations")
public class AdminInvitationController {

  private final InvitationFacade userFacade;

  @GetMapping
  public List<InvitationDto> getAllInvitations() {
    log.debug("Get all invitations");
    return userFacade.getAllInvitations();
  }
}
