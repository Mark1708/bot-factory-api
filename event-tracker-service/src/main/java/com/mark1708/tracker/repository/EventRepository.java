package com.mark1708.tracker.repository;

import com.mark1708.tracker.model.entities.Event;
import com.mark1708.tracker.model.entities.clicker.ClickEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
