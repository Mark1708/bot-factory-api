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
@Table(name = "users")
public class UsersStatistics {

  @Id
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
  @Column(length = 36, nullable = false, updatable = false)
  private String id;

  @Column(name = "date_time", columnDefinition = "TIMESTAMP")
  private LocalDateTime dateTime;
  
  @Column(name = "bot_id")
  private Long botId;
  
  @Column(name = "count_all")
  private Long countAll;
  
  @Column(name = "count_blocked")
  private Long countBlocked;
  
  @Column(name = "count_today_online")
  private Long countTodayOnline;
  
  @Column(name = "count_today_registered")
  private Long countTodayRegistered;
  
  @Column(name = "count_with_no_payments")
  private Long countWithNoPayments;

  @Column(name = "count_with_more_one_payments")
  private Long countWithMoreOnePayments;

  @Column(name = "count_with_more_five_payments")
  private Long countWithMoreFivePayments;

  @Column(name = "count_with_more_ten_payments")
  private Long countWithMoreTenPayments;
  
}
