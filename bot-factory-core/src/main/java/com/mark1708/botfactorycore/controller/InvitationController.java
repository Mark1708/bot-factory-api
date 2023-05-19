package com.mark1708.botfactorycore.controller;


import com.mark1708.botfactorycore.facade.InvitationFacade;
import com.mark1708.botfactorycore.model.invitation.ApplyInvitationDto;
import com.mark1708.botfactorycore.model.invitation.CreateInvitationDto;
import com.mark1708.botfactorycore.model.invitation.InvitationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/factory/invitations")
public class InvitationController {

  private final InvitationFacade invitationFacade;

  @GetMapping("/{id}")
  public InvitationDto getInvitation(@PathVariable Long id) {
    log.debug("Get invitation: [{}]", id);
    return invitationFacade.getInvitation(id);
  }

  @PostMapping()
  public InvitationDto createInvitation(@RequestBody CreateInvitationDto createInvitationDto) {
    log.debug("Create invitation: [{}]", createInvitationDto);
    return invitationFacade.createInvitation(createInvitationDto);
  }

  @PutMapping("/{id}")
  public InvitationDto applyInvitation(
      @PathVariable Long id,
      @RequestBody ApplyInvitationDto applyInvitationDto) {
    log.debug("Apply invitation: [{}, {}]", id, applyInvitationDto);
    return invitationFacade.applyInvitation(id, applyInvitationDto);
  }

  @DeleteMapping("/{id}")
  public boolean deleteInvitation(@PathVariable Long id) {
    log.debug("Delete invitation: [{}]", id);
    return invitationFacade.deleteInvitation(id);
  }

}
