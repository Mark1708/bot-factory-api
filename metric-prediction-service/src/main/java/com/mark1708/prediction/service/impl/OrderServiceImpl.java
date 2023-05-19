package com.mark1708.prediction.service.impl;

import com.mark1708.prediction.exception.http.QueryType;
import com.mark1708.prediction.exception.http.ResourceNotFoundException;
import com.mark1708.prediction.exception.http.ResourceType;
import com.mark1708.prediction.model.Order;
import com.mark1708.prediction.repository.OrderRepository;
import com.mark1708.prediction.service.OrderService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository repository;

  @Override
  public Order getOrderById(String id) {
    return repository.findById(id).orElseThrow(() ->
        new ResourceNotFoundException(ResourceType.ORDER, QueryType.ID, id)
    );
  }

  @Override
  public Optional<Order> findLastWaitingOrder() {
    return repository.findLastWaitingOrder();
  }

  @Override
  public Order saveOrder(Order order) {
    return repository.saveAndFlush(order);
  }

  @Override
  @Transactional
  public void updateStatus(String id, int status) {
    repository.updateStatus(id, status);
  }
}
