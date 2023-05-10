package com.mark1708.botfactorycore.repository;

import com.mark1708.botfactorycore.model.entity.Invitation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {

  Optional<Invitation> findInvitationByEmailAndCode(String email, String code);

  List<Invitation> getInvitationsByCompanyId(Long id);
}
