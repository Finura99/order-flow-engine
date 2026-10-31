package com.arya.orderflow;
import com.arya.orderflow.domain.OrderSide;
import com.arya.orderflow.domain.OrderStatus;
import com.arya.orderflow.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import com.arya.orderflow.domain.Order;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderRepoTest {

    @Autowired
    private OrderRepository repository;

    @BeforeEach
    void setup() {

    }

    @Test
    void persistenceForFindByIdTest() {

        Order order = new Order(
                1,
                "AAPL",
                OrderSide.BUY,
                10,
                150.23
        );

        repository.save(order);
        //save it

        Optional<Order> result = repository.findById(1);
        //find it again

        assertTrue(result.isPresent()); // it exists
        assertEquals("AAPL", result.get().getSymbol()); //symbol is correct
    }

    @Test
    void persistenceForFindByStatusTest() {

        repository.deleteAll();

        Order order = new Order(
                1,
                "MSFT",
                OrderSide.BUY,
                33,
                302.32
        );

        Order order2 = new Order(
                2,
                "MSFT",
                OrderSide.BUY,
                33,
                302.32
        );

        Order order3 = new Order(
                3,
                "MSFT",
                OrderSide.BUY,
                -33,
                302.32
        );

        order.markFilled();
        order2.markFilled();
        order3.markRejected();

        repository.save(order);
        repository.save(order2);
        repository.save(order3);
        //ARRANGE

        List<Order> statusResult = repository.findByStatus(OrderStatus.FILLED);
        //ACT

        assertEquals(2, statusResult.size());
        assertTrue(statusResult.stream().anyMatch(o -> o.getId() == 1));
        assertTrue(statusResult.stream().anyMatch(o -> o.getId() == 1));
        assertFalse(statusResult.stream().anyMatch(o -> o.getId() == 3));

        //.stream() means look through the returned data
        //anyMatch() means does any orders satisfy this condition
        // o -> o.getId() means is there any orders that match the id's

    }
}
