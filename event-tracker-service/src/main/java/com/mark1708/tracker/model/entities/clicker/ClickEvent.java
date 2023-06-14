package com.mark1708.tracker.model.entities.clicker;

import com.mark1708.tracker.model.entities.Event;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "click_events")
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "event_id")
public class ClickEvent extends Event {

  @ManyToOne
  @JoinColumn(name = "clicker_info_id", referencedColumnName = "id")
  private ClickerInfo clickerInfo;

  public ClickEvent(Event event, ClickerInfo clickerInfo) {
    super(event);
    this.clickerInfo = clickerInfo;
  }
}
