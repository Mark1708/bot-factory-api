package com.mark1708.prediction.repository;

import com.mark1708.prediction.model.Order;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

  @Modifying
  @Query(value = "UPDATE Order o SET o.status = :status WHERE o.id = :id")
  void updateStatus(@Param("id") String id, @Param("status") int status);

  @Query(value = "SELECT o.* FROM orders o WHERE o.status = 0 ORDER BY o.created_at LIMIT 1",
      nativeQuery = true)
  Optional<Order> findLastWaitingOrder();
}
