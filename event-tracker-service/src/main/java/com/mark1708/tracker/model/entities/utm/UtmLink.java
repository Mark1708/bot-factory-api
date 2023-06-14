package com.mark1708.tracker.model.entities.utm;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "utm_links")
public class UtmLink {

  @Id
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
  @Column(length = 36, nullable = false, updatable = false)
  private String id;

  @Column(name = "bot_id")
  private Long botId;

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

  @Column(name = "create_at")
  private LocalDateTime createAt;
}
