package com.mark1708.botapicore.model.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tariffs")
public class Tariff {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @ToString.Exclude
  @JoinColumn(name = "service_id", referencedColumnName = "id")
  private Service service;

  @Column(name = "time_unit")
  private Integer timeUnit;

  @Column(name = "value")
  private Long value;

  @Column(name = "price")
  private Long price;

  @ToString.Exclude
  @OneToMany(mappedBy = "tariff")
  private List<Pay> pays;
}
