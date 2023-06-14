package com.mark1708.tracker.model.entities;

import com.mark1708.tracker.model.enums.EventType;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "events")
@Inheritance(strategy = InheritanceType.JOINED)
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "bot_id")
  private Long botId;

  @Column(name = "user_id")
  private Long userId;

  @Basic
  @Column(name = "type")
  private int typeValue;

  @Column(name = "create_at")
  private LocalDateTime createAt;

  @Transient
  private EventType type;

  @PostLoad
  void fillTransient() {
    if (typeValue > 0) {
      this.type = EventType.of(typeValue);
    }
  }

  @PrePersist
  void fillPersistent() {
    if (type != null) {
      this.typeValue = type.getValue();
    }
  }

  public Event(Event event) {
    this.id = event.id;
    this.botId = event.botId;
    this.userId = event.userId;
    this.typeValue = event.typeValue;
    this.createAt = event.createAt;
    this.type = event.type;
  }
}
