package com.mark1708.botapicore.repository;

import com.mark1708.botapicore.model.entity.Pay;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PayRepository extends JpaRepository<Pay, Long> {

  @Query(value = "SELECT SUM(p.amount) FROM pays p "
      + "INNER JOIN services s ON s.id = p.service_id "
      + "WHERE s.bot_id = :botId", nativeQuery = true)
  long getTotalAmountByBotId(@Param("botId") long botId);

  @Query(value = "SELECT COUNT(p.amount) FROM pays p "
      + "INNER JOIN services s ON s.id = p.service_id "
      + "WHERE s.bot_id = :botId", nativeQuery = true)
  long getTotalCountByBotId(@Param("botId") long botId);

  @Query(value = "SELECT SUM(p.amount) FROM pays p "
      + "WHERE p.service_id = :serviceId", nativeQuery = true)
  long getTotalAmountByServiceId(@Param("serviceId") long serviceId);

  @Query(value = "SELECT COUNT(p.amount) FROM pays p "
      + "WHERE p.service_id = :serviceId", nativeQuery = true)
  long getTotalCountByServiceId(@Param("serviceId") long serviceId);

  @Query(value = "SELECT SUM(p.amount) FROM pays p "
      + "WHERE p.user_id = :userId", nativeQuery = true)
  long getTotalAmountByUserId(@Param("userId") long userId);

  @Query(value = "SELECT COUNT(p.amount) FROM pays p "
      + "WHERE p.user_id = :userId", nativeQuery = true)
  long getTotalCountByUserId(@Param("userId") long userId);

  @Query(value = "SELECT SUM(p.amount) FROM pays p "
      + "INNER JOIN services s ON s.id = p.service_id "
      + "WHERE s.bot_id = :botId "
      + "AND p.pay_date >= date_trunc('month', now())", nativeQuery = true)
  long getTotalAmountByBotIdAtCurrentMonth(@Param("botId") long botId);

  @Query(value = "SELECT COUNT(p.amount) FROM pays p "
      + "INNER JOIN services s ON s.id = p.service_id "
      + "WHERE s.bot_id = :botId "
      + "AND p.pay_date >= date_trunc('month', now())", nativeQuery = true)
  long getTotalCountByBotIdAtCurrentMonth(@Param("botId") long botId);

  @Query(value = "SELECT SUM(p.amount) FROM pays p "
      + "WHERE p.service_id = :serviceId "
      + "AND p.pay_date >= date_trunc('month', now())", nativeQuery = true)
  long getTotalAmountByServiceIdAtCurrentMonth(@Param("serviceId") long serviceId);

  @Query(value = "SELECT COUNT(p.amount) FROM pays p "
      + "WHERE p.service_id = :serviceId "
      + "AND p.pay_date >= date_trunc('month', now())", nativeQuery = true)
  long getTotalCountByServiceIdAtCurrentMonth(@Param("serviceId") long serviceId);

  @Query(value = "SELECT SUM(p.amount) FROM pays p "
      + "WHERE p.user_id = :userId "
      + "AND p.pay_date >= date_trunc('month', now())", nativeQuery = true)
  long getTotalAmountByUserIdAtCurrentMonth(@Param("userId") long userId);

  @Query(value = "SELECT COUNT(p.amount) FROM pays p "
      + "WHERE p.user_id = :userId "
      + "AND p.pay_date >= date_trunc('month', now())", nativeQuery = true)
  long getTotalCountByUserIdAtCurrentMonth(@Param("userId") long userId);

  @Query(value = "SELECT p.* FROM pays p "
      + "INNER JOIN services s ON p.service_id = s.id "
      + "WHERE s.bot_id = :botId"
      , nativeQuery = true)
  List<Pay> findAllByBotId(@Param("botId") long botId);

  @Query(value = "SELECT p.* FROM pays p "
      + "INNER JOIN services s ON p.service_id = s.id "
      + "WHERE s.bot_id = :botId AND s.id = :serviceId"
      , nativeQuery = true)
  List<Pay> findAllByBotIdAndServiceId(
      @Param("botId") long botId,
      @Param("serviceId") long serviceId
  );

  @Query(value = "SELECT p.* FROM pays p "
      + "INNER JOIN users u on u.id = p.user_id "
      + "WHERE u.bot_id = :botId AND u.id = :userId"
      , nativeQuery = true)
  List<Pay> findAllByBotIdAndUserId(@Param("botId") long botId, @Param("userId") long userId);
}
