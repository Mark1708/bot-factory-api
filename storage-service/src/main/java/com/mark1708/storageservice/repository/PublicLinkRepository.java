package com.mark1708.storageservice.repository;

import com.mark1708.storageservice.model.entity.PublicLink;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicLinkRepository extends JpaRepository<PublicLink, Long> {

  Optional<PublicLink> findByFileId(long fileId);

  Optional<PublicLink> findByToken(String token);

  void deleteByFileId(long fileId);
}
