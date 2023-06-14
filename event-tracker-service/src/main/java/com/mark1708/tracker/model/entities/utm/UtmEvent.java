package com.mark1708.tracker.model.entities.utm;

import com.mark1708.tracker.model.entities.Event;
import javax.persistence.Column;
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
@Table(name = "utm_events")
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "event_id")
public class UtmEvent extends Event {

  @ManyToOne
  @JoinColumn(name = "source_id", referencedColumnName = "id")
  private UtmSource source;

  @ManyToOne
  @JoinColumn(name = "medium_id", referencedColumnName = "id")
  private UtmMedium medium;

  @ManyToOne
  @JoinColumn(name = "campaign_id", referencedColumnName = "id")
  private UtmCampaign campaign;

  @ManyToOne
  @JoinColumn(name = "content_id", referencedColumnName = "id")
  private UtmContent content;

  @ManyToOne
  @JoinColumn(name = "term_id", referencedColumnName = "id")
  private UtmTerm term;

  @Column(name = "is_new_user")
  private boolean newUser;


  public UtmEvent(Event event, UtmSource source, UtmMedium medium,
      UtmCampaign campaign, UtmContent content, UtmTerm term, boolean newUser) {
    super(event);
    this.source = source;
    this.medium = medium;
    this.campaign = campaign;
    this.content = content;
    this.term = term;
    this.newUser = newUser;
  }
}
