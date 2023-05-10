package com.mark1708.botfactorycore.model.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "projects")
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @ToString.Exclude
  @JoinColumn(name = "company_id", referencedColumnName = "id")
  private Company company;

  @Column(name = "bot_id")
  private Long botId;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "slug")
  private String slug;

  @Column(name = "logo_url")
  private String logoUrl;

  @Column(name = "bg_color")
  private String bgColor;

  @Column(name = "text_color")
  private String textColor;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "api_key")
  private String apiKey;

  @Column(name = "webhook_path")
  private String webhookPath;

  @Column(name = "active")
  private boolean active;


  @ToString.Exclude
  @ManyToMany(mappedBy = "projects")
  private List<User> users;
}
