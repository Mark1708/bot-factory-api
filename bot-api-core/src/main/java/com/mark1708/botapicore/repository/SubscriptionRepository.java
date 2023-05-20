package com.mark1708.botapicore.repository;

import com.mark1708.botapicore.model.entity.Subscription;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

//  Optional<Subscription> findByIdAndBotId(long id, long botId);

  @Query(value = "SELECT sub.* FROM subscriptions sub "
      + "INNER JOIN services s on s.id = sub.service_id "
      + "WHERE s.id = :serviceId AND s.bot_id = :botId"
      , nativeQuery = true)
  List<Subscription> findSubscriptionsByBotIdAndServiceId(
      @Param("botId") long botId,
      @Param("serviceId") long serviceId
  );

  @Query(value = "SELECT sub.* FROM subscriptions sub "
      + "INNER JOIN users u on u.id = sub.user_id "
      + "WHERE u.id = :userId AND u.bot_id = :botId"
      , nativeQuery = true)
  List<Subscription> findSubscriptionsByBotIdAndUserId(
      @Param("botId") long botId,
      @Param("userId") long userId
  );


}
