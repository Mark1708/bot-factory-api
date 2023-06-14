package com.mark1708.tracker.repository.utm;

import com.mark1708.tracker.model.entities.utm.UtmContent;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtmContentRepository extends JpaRepository<UtmContent, Long> {

  Optional<UtmContent> findByName(String name);
}
