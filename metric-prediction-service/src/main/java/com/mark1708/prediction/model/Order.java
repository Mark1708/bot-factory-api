package com.mark1708.prediction.model;

import com.mark1708.clients.prediction.dto.PredictedItem;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@TypeDefs({@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)})
public class Order {

  @Id
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
  @Column(length = 36, nullable = false, updatable = false)
  private String id;

  @Column(name = "bot_id")
  private Long botId;

  @Column(name = "service_id")
  private Long serviceId;

  @Column(name = "tariff_id")
  private Long tariffId;

  @Column(name = "days")
  private Integer days;

  @Column(name = "type")
  private String type;

  @Column(name = "value")
  private String value;

  @Column(name = "created_at", columnDefinition = "TIMESTAMP")
  private LocalDateTime createdAt;

  @Column(name = "start_at", columnDefinition = "TIMESTAMP")
  private LocalDateTime startAt;

  @Column(name = "end_at", columnDefinition = "TIMESTAMP")
  private LocalDateTime endAt;


  /**
   * 0 - waiting
   * 1 - retrieving data
   * 2 - forecasting data
   * 3 - done
   * 4 - end with error
   * 5 - need more
   * 6 - bad request
   * data
   */
  @Column(name = "status")
  private Integer status;

  @ToString.Exclude
  @Type(type = "jsonb")
  @Column(name = "result", columnDefinition = "jsonb")
  @Basic(fetch = FetchType.LAZY)
  private List<PredictedItem> result;
}
