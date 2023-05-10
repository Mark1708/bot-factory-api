package com.mark1708.botfactorycore.facade.impl;

import com.mark1708.botfactorycore.converter.InvitationConverter;
import com.mark1708.botfactorycore.exception.http.BadRequestException;
import com.mark1708.botfactorycore.facade.InvitationFacade;
import com.mark1708.botfactorycore.model.entity.Invitation;
import com.mark1708.botfactorycore.model.entity.User;
import com.mark1708.botfactorycore.model.invitation.ApplyInvitationDto;
import com.mark1708.botfactorycore.model.invitation.CreateInvitationDto;
import com.mark1708.botfactorycore.model.invitation.InvitationDto;
import com.mark1708.botfactorycore.service.CompanyService;
import com.mark1708.botfactorycore.service.InvitationService;
import com.mark1708.botfactorycore.service.UserService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvitationFacadeImpl implements InvitationFacade {

  private final InvitationService invitationService;
  private final CompanyService companyService;
  private final UserService userService;

  private final InvitationConverter invitationConverter;

  @Override
  public List<InvitationDto> getAllInvitations() {
    return invitationConverter.toDto(
        invitationService.getAllInvitations()
    );
  }

  @Override
  public InvitationDto getInvitation(Long id) {
    return invitationConverter.toDto(
        invitationService.getInvitation(id)
    );
  }

  @Override
  public InvitationDto createInvitation(CreateInvitationDto createInvitationDto) {
    Invitation invitation = Invitation.builder()
        .code(generateRandomCode())
        .companyId(createInvitationDto.getCompanyId())
        .email(createInvitationDto.getEmail())
        .sendAt(LocalDateTime.now())
        .confirm(false)
        .build();
    return invitationConverter.toDto(
        invitationService.saveInvitation(invitation)
    );
  }

  @Override
  public InvitationDto applyInvitation(Long id, ApplyInvitationDto applyInvitationDto) {
    User user = userService.getUserByEmail(applyInvitationDto.getEmail());

    Invitation invitation = invitationService.getInvitationByEmailAndCode(
        applyInvitationDto.getEmail(),
        applyInvitationDto.getCode()
    );

    if (invitation.isConfirm()) {
      throw new BadRequestException("Invitation already applied");
    }

    companyService.addUserToCompany(invitation.getCompanyId(), user.getId());

    invitation.setConfirm(true);
    invitation.setConfirmAt(LocalDateTime.now());

    return invitationConverter.toDto(
        invitationService.saveInvitation(invitation)
    );

  }

  @Override
  public boolean deleteInvitation(Long id) {
    return invitationService.deleteInvitation(id);
  }

  private String generateRandomCode() {
    Random random = new Random();
    int firstNum = random.nextInt(999) + 1;
    int secondNum = random.nextInt(999) + 1;
    return String.format("%03d-%03d", firstNum, secondNum);
  }
}
