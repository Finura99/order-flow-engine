package com.arya.orderflow.repository;

import com.arya.orderflow.domain.Order;
import com.arya.orderflow.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {


    List<Order> findByStatus(OrderStatus status);

}
