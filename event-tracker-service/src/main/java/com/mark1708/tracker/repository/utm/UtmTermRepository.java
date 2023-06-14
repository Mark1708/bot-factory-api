package com.mark1708.tracker.repository.utm;

import com.mark1708.tracker.model.entities.utm.UtmTerm;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtmTermRepository extends JpaRepository<UtmTerm, Long> {

  Optional<UtmTerm> findByName(String name);
}
