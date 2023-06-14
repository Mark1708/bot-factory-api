package com.mark1708.tracker.model.entities.clicker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clicker_info")
@JsonIgnoreProperties(value = {"events", "botId"})
public class ClickerInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "bot_id")
  private Long botId;

  @Column(name = "name")
  private String name;

  @Column(name = "slug")
  private String slug;

  @Column(name = "create_at")
  private LocalDateTime createAt;

  @Column(name = "update_at")
  private LocalDateTime updateAt;

  @OneToMany(mappedBy = "clickerInfo")
  private List<ClickEvent> events;
}
