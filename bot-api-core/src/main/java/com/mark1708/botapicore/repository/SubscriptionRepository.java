package com.mark1708.botapicore.repository;

import com.mark1708.botapicore.model.entity.Subscription;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

  Optional<Subscription> findByUserIdAndServiceId(long userId, long serviceId);

  List<Subscription> findAllByUserId(long userId);

//  @Query(value = "SELECT s.* FROM subscriptions s "
//      + "WHERE s.available_count = 0 OR s.end_date", nativeQuery = true)
//  List<Subscription> findAllExpiredSubscriptions();
}
