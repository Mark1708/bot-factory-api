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
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subscriptions")
public class Subscription {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @ToString.Exclude
  @JoinColumn(name = "service_id", referencedColumnName = "id")
  private Service service;

  @ManyToOne
  @ToString.Exclude
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @Column(name = "active")
  private boolean active;

  @Column(name = "need_notify")
  private boolean needNotify;

  @Column(name = "notified")
  private boolean notified;

  @Column(name = "trial_period_start_date")
  private LocalDateTime trialPeriodStartDate;

  @Column(name = "trial_period_end_date")
  private LocalDateTime trialPeriodEndDate;

  @Column(name = "subscribe_after_trial")
  private boolean subscribeAfterTrial;

  @Column(name = "trial_count")
  private Long trialCount;

  @Column(name = "available_count")
  private Long availableCount;

  @Column(name = "end_date")
  private LocalDateTime endDate;
}
