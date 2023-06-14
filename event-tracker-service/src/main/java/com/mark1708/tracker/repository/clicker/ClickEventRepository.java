package com.mark1708.tracker.repository.clicker;

import com.mark1708.tracker.model.entities.clicker.ClickEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClickEventRepository extends JpaRepository<ClickEvent, Long> {

}
