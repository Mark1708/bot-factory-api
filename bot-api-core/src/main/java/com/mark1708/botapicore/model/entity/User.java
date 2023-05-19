package com.mark1708.botapicore.model.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import javax.persistence.Basic;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@TypeDefs({@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)})
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "bot_id", referencedColumnName = "id")
  private Bot bot;

  @Column(name = "platform_id")
  private String platformId;

  @Column(name = "username")
  private String username;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "blocked")
  private boolean blocked;

  @Column(name = "state")
  private String state;

  @Column(name = "registered_at")
  private LocalDateTime registeredAt;

  @Column(name = "last_activity_at")
  private LocalDateTime lastActivityAt;

  @Type(type = "jsonb")
  @Basic(fetch = FetchType.EAGER)
  @Column(name = "additional_attributes", columnDefinition = "jsonb")
  private Map<String, Object> additionalAttributes;

  @ManyToMany
  @ToString.Exclude
  @JoinTable(
      name = "users_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private List<Role> roles;

  @ToString.Exclude
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
  private List<Pay> pays;

  @ToString.Exclude
  @OneToMany(mappedBy = "user")
  private List<Subscription> subscriptions;
}
