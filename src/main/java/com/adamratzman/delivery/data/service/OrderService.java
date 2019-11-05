package com.adamratzman.delivery.data.service;

import com.adamratzman.delivery.data.dao.OrderRepository;
import com.adamratzman.delivery.models.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
  private OrderRepository orderRepository;

  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public Optional<Order> findOrder(long id) {
    return orderRepository.findById(id);
  }

  public List<Order> findAll() {
    return (List<Order>) orderRepository.findAll();
  }

  public void removeOrder(long id) {
    orderRepository.deleteById(id);
  }

  public void addOrUpdateOrder(Order order) {
    orderRepository.save(order);
  }
}
