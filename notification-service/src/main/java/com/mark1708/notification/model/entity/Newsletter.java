package com.mark1708.notification.model.entity;

import com.mark1708.notification.model.enums.Messenger;
import java.time.Instant;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "newsletter")
public class Newsletter {

  @Id
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
  @Column(length = 36, nullable = false, updatable = false)
  private String id;

  @Column(name = "bot_id")
  private Long botId;

  @Basic
  @Column(name = "messenger")
  private int messengerValue;

  @Transient
  private Messenger messenger;

  @Column(name = "send_at")
  private Instant sendAt;

  @Column(name = "is_deleted")
  private boolean isDeleted;

  @ToString.Exclude
  @OneToMany(mappedBy = "newsletter")
  private List<Message> messages;

  @PostLoad
  void fillMessenger() {
    this.messenger = Messenger.of(messengerValue);
  }

  @PrePersist
  void fillMessengerValue() {
    this.messengerValue = this.messenger.getMessenger();
  }
}
