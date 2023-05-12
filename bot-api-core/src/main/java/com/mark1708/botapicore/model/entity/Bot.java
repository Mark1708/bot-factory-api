package com.mark1708.botapicore.model.entity;

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
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bots")
public class Bot {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "api_key")
  private String apiKey;

  @Column(name = "webhook_path")
  private String webhookPath;

  @Column(name = "active")
  private boolean active;

  @ToString.Exclude
  @OneToMany(mappedBy = "bot")
  private List<Role> roles;

  @ToString.Exclude
  @OneToMany(mappedBy = "bot")
  private List<Service> services;

  @ToString.Exclude
  @OneToMany(mappedBy = "bot")
  private List<User> users;
}
