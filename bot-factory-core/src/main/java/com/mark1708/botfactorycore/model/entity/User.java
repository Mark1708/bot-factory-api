package com.mark1708.botfactorycore.model.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "surname")
  private String surname;

  @Column(name = "name")
  private String name;

  @Column(name = "username")
  private String username;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "salt")
  private String salt;

  @Column(name = "registered_at")
  private LocalDateTime registeredAt;

  @Column(name = "enabled")
  private boolean enabled;

  @Column(name = "email_verified")
  private boolean emailVerified;

  @ToString.Exclude
  @ManyToMany(fetch = FetchType.EAGER, mappedBy = "users")
  private List<Role> roles;

  @ToString.Exclude
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinTable(
      name = "companies_users",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "company_id")
  )
  private Company company;

  @ToString.Exclude
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "projects_users",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "project_id")
  )
  private List<Project> projects;

}
