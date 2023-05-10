package com.mark1708.botfactorycore.model.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "companies")
public class Company {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "owner_id")
  private Long ownerId;

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

  @ToString.Exclude
  @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
  private List<Project> projects;

  @ToString.Exclude
  @OneToMany(mappedBy = "company")
  private List<User> users;
}
