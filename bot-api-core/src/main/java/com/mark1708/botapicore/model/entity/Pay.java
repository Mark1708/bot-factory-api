package com.mark1708.botapicore.model.entity;

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
import lombok.ToString;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pays")
public class Pay {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @ToString.Exclude
  @JoinColumn(name = "service_id", referencedColumnName = "id")
  private SubService service;

  @ManyToOne
  @ToString.Exclude
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @ManyToOne
  @ToString.Exclude
  @JoinColumn(name = "tariff_id", referencedColumnName = "id")
  private Tariff tariff;

  @Column(name = "pay_date")
  private LocalDateTime payDate;

  @Column(name = "payload")
  private String payload;

  @Column(name = "amount")
  private Long amount;
}
