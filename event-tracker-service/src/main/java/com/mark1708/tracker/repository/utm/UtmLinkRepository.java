package com.mark1708.tracker.repository.utm;

import com.mark1708.tracker.model.entities.utm.UtmLink;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtmLinkRepository extends JpaRepository<UtmLink, String> {

  Optional<UtmLink> findByBotIdAndAndId(Long botId, String id);
}
