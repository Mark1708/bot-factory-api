package com.mark1708.botfactorycore.model.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company_invitations")
public class Invitation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "company_id")
  private Long companyId;

  @Column(name = "email")
  private String email;

  @Column(name = "code")
  private String code;

  @Column(name = "confirm")
  private boolean confirm;

  @Column(name = "send_at")
  private LocalDateTime sendAt;

  @Column(name = "confirm_at")
  private LocalDateTime confirmAt;

}
