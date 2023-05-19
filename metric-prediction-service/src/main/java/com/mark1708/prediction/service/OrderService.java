package com.mark1708.prediction.service;

import com.mark1708.prediction.model.Order;
import java.util.Optional;

public interface OrderService {

  Order getOrderById(String id);

  Optional<Order> findLastWaitingOrder();

  Order saveOrder(Order order);

  void updateStatus(String id, int status);

}
