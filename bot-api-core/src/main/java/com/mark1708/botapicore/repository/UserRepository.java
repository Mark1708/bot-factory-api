package com.mark1708.botapicore.repository;

import com.mark1708.botapicore.model.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByPlatformId(String platformId);
  Optional<User> findByUsername(String username);

  Optional<User> findByPlatformIdAndBotId(String platformId, long botId);
  Optional<User> findByUsernameAndBotId(String username, long botId);

  @Query(value = "SELECT u.* FROM users u "
      + "WHERE concat_ws(' ', u.last_name, u.first_name) LIKE '%' || :query || '%' "
      + "AND u.bot_id = :botId", nativeQuery = true)
  List<User> searchByFullnNameByBotId(@Param("query") String query, @Param("botId") long botId);

  List<User> findAllByBotId(long botId);
  List<User> findAllByBotIdAndState(long botId, String state);

  @Query(value = "SELECT u.* FROM users u "
      + "WHERE u.bot_id = :botId AND "
      + "u.registered_at >= date_trunc('month', now())", nativeQuery = true)
  List<User> findAllRegisteredAtCurrentMonth(@Param("botId") long botId);

  @Query(value = "SELECT u.* FROM users u "
      + "WHERE u.bot_id = :botId AND "
      + "u.last_activity_at >= date_trunc('month', now())", nativeQuery = true)
  List<User> findAllLastActiveAtCurrentMonth(@Param("botId") long botId);

  @Query(value = "SELECT COUNT(*) FROM users u "
      + "WHERE u.bot_id = :botId AND "
      + "u.registered_at >= date_trunc('month', now())", nativeQuery = true)
  long countRegistrationAtCurrentMonth(@Param("botId") long botId);

  @Query(value = "SELECT COUNT(*) FROM users u "
      + "WHERE u.bot_id = :botId AND "
      + "u.last_activity_at >= date_trunc('month', now())", nativeQuery = true)
  long countLastActivityAtCurrentMonth(@Param("botId") long botId);
}
