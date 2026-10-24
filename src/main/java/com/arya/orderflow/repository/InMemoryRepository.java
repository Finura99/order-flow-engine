package com.arya.orderflow.repository;

import com.arya.orderflow.domain.Order;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository {
    // DOA Layer...
    private final Map<Integer, Order>orders = new HashMap<>();
    // in memory/cache saved within the jvm, no persistence
    public void save(Order order) {
        orders.put(order.getId(), order);

        // keys are int id and values are Order Objects
        // put params are (key, value)
    }

    public Order findById(int id){
        return orders.get(id);
    }
}

// Orders.put and get come from Javas map interface
