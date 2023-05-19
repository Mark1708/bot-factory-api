package com.mark1708.botfactorycore.converter;

import com.mark1708.botfactorycore.model.entity.Invitation;
import com.mark1708.botfactorycore.model.invitation.InvitationDto;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class InvitationConverter {

  public InvitationDto toDto(Invitation invitation) {
    InvitationDto result = new InvitationDto();
    BeanUtils.copyProperties(invitation, result);
    return result;
  }

  public List<InvitationDto> toDto(Collection<Invitation> invitations) {
    return invitations
        .stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }
}
