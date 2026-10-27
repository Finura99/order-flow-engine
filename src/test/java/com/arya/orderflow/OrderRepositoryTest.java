package com.arya.orderflow;

import com.arya.orderflow.domain.Order;
import com.arya.orderflow.domain.OrderSide;
import com.arya.orderflow.domain.OrderStatus;
import com.arya.orderflow.repository.InMemoryRepository;
import com.arya.orderflow.repository.OrderRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class OrderRepositoryTest {


    @Test
    void returnOrderTest() {

        //Arrange
        InMemoryRepository repository = mock(OrderRepository.class);
        Order order = new Order(
                1,
                "AAPL",
                OrderSide.BUY,
                117,
                328.20
        );

        //Act
        repository.save(order);
        Order result = repository.findById(1);

        //Assert
        assertEquals(order, result);
        // params(expected, actual)

    }

    @Test
    void returnOrderByOrderStatus() {
        //Arrange
        InMemoryRepository repository = new InMemoryRepository();

        Order order = new Order(
                1,
                "MSFT",
                OrderSide.BUY,
                200,
                321.20
        );

        Order order2 = new Order(
                2,
                "MSFT",
                OrderSide.BUY,
                200,
                321.20
        );

        Order order3 = new Order(
                3,
                "NVDA",
                OrderSide.BUY,
                300,
                221.40
        );

        order.markFilled();
        order2.markFilled();
        order3.markRejected();

        repository.save(order);
        repository.save(order2);
        repository.save(order3);

        List<Order> result = repository.findByOrderStatus(OrderStatus.FILLED);

        assertEquals(2, result.size());
        assertTrue(result.contains(order));
        assertTrue(result.contains(order2));
        assertFalse(result.contains(order3));



    }


}
