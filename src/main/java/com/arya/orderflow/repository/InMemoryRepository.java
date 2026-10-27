package com.arya.orderflow.repository;

import com.arya.orderflow.domain.Order;
import com.arya.orderflow.domain.OrderStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class InMemoryRepository {
    // DOA Layer...
    private final Map<Integer, Order>orders = new HashMap<>();
    // in memory/cache saved within the jvm, no persistence


    public void save(Order order) {

        orders.put(order.getId(), order);

        // put params are (key, value). Key is order id and value is the order object
    }


    public Order findById(int id){
        return orders.get(id);
        // Orders.put and get come from Javas map interface
    }

    //return list of filled orders method
    public List<Order> findByOrderStatus(OrderStatus status) {
        List<Order> orderStatusList = new ArrayList<>();
        // empty list

        for (Order order: orders.values()) {
            if (order.getStatus() == status) {
                orderStatusList.add(order);
            }
        }
        // Hashmap scan of our in memory list

        return orderStatusList;


    }
}

