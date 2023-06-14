package com.mark1708.tracker.repository.utm;

import com.mark1708.tracker.model.entities.utm.UtmEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtmEventRepository extends JpaRepository<UtmEvent, Long> {

}
