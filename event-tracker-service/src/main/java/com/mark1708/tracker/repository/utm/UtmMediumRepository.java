package com.mark1708.tracker.repository.utm;

import com.mark1708.tracker.model.entities.utm.UtmMedium;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtmMediumRepository extends JpaRepository<UtmMedium, Long> {

  Optional<UtmMedium> findByName(String name);
}
