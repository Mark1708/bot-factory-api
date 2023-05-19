package com.mark1708.statistics.repository;

import com.mark1708.statistics.model.entity.StatisticsItem;
import com.mark1708.statistics.model.entity.UsersStatistics;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersStatisticsRepository extends JpaRepository<UsersStatistics, Long> {

  String SELECT_DATE_AND = "SELECT u.date_time as dateTime, ";
  String AS_VALUE = " AS value";
  String FROM_USERS = " FROM users u ";
  String BOT_EQ = "WHERE u.bot_id = :botId ";
  String ORDER = "ORDER BY u.date_time";
  String COUNT_ALL = "u.count_all";
  String COUNT_BLOCKED = "u.count_blocked";
  String COUNT_ACTIVE = "u.count_all - u.count_blocked";
  String COUNT_TODAY_ONLINE = "u.count_today_online";
  String COUNT_TODAY_REG = "u.count_today_registered";
  String COUNT_NO_PAY = "u.count_with_no_payments";
  String COUNT_ONE_PAY = "u.count_with_more_one_payments";
  String COUNT_FIVE_PAY = "u.count_with_more_five_payments";
  String COUNT_TEN_PAY = "u.count_with_more_ten_payments";

  @Query(value = SELECT_DATE_AND + COUNT_ALL + AS_VALUE + FROM_USERS + BOT_EQ + ORDER, nativeQuery = true)
  List<StatisticsItem> findCountAllByBotId(@Param(value = "botId") long botId);

  @Query(value = SELECT_DATE_AND + COUNT_ACTIVE + AS_VALUE + FROM_USERS + BOT_EQ + ORDER, nativeQuery = true)
  List<StatisticsItem> findCountActiveByBotId(@Param(value = "botId") long botId);

  @Query(value = SELECT_DATE_AND + COUNT_BLOCKED + AS_VALUE + FROM_USERS + BOT_EQ + ORDER, nativeQuery = true)
  List<StatisticsItem> findCountBlockedByBotId(@Param(value = "botId") long botId);

  @Query(value = SELECT_DATE_AND + COUNT_TODAY_ONLINE + AS_VALUE + FROM_USERS + BOT_EQ + ORDER, nativeQuery = true)
  List<StatisticsItem> findCountTodayOnlineByBotId(@Param(value = "botId") long botId);

  @Query(value = SELECT_DATE_AND + COUNT_TODAY_REG + AS_VALUE + FROM_USERS + BOT_EQ + ORDER, nativeQuery = true)
  List<StatisticsItem> findCountTodayRegisteredByBotId(@Param(value = "botId") long botId);

  @Query(value = SELECT_DATE_AND + COUNT_NO_PAY + AS_VALUE + FROM_USERS + BOT_EQ + ORDER, nativeQuery = true)
  List<StatisticsItem> findCountNoPaymentsByBotId(@Param(value = "botId") long botId);

  @Query(value = SELECT_DATE_AND + COUNT_ONE_PAY + AS_VALUE + FROM_USERS + BOT_EQ + ORDER, nativeQuery = true)
  List<StatisticsItem> findCountOnePaymentsByBotId(@Param(value = "botId") long botId);

  @Query(value = SELECT_DATE_AND + COUNT_FIVE_PAY + AS_VALUE + FROM_USERS + BOT_EQ + ORDER, nativeQuery = true)
  List<StatisticsItem> findCountFivePaymentsByBotId(@Param(value = "botId") long botId);

  @Query(value = SELECT_DATE_AND + COUNT_TEN_PAY + AS_VALUE + FROM_USERS + BOT_EQ + ORDER, nativeQuery = true)
  List<StatisticsItem> findCountTenPaymentsByBotId(@Param(value = "botId") long botId);
}
