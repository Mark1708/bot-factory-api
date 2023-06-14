package com.mark1708.tracker.repository.utm;

import com.mark1708.tracker.model.entities.utm.UtmSource;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtmSourceRepository extends JpaRepository<UtmSource, Long> {

  Optional<UtmSource> findByName(String name);
}
