package com.mark1708.statistics.model.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pays")
public class PaysStatistics {

  @Id
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
  @Column(length = 36, nullable = false, updatable = false)
  private String id;

  @Column(name = "date_time", columnDefinition = "TIMESTAMP")
  private LocalDateTime dateTime;

  @Column(name = "bot_id")
  private Long botId;

  @Column(name = "service_id")
  private Long serviceId;

  @Column(name = "tariff_id")
  private Long tariffId;

  @Column(name = "count_all")
  private Long countAll;

  @Column(name = "all_income")
  private Long allIncome;

  @Column(name = "count_today")
  private Long countToday;

  @Column(name = "today_income")
  private Long todayIncome;

}
