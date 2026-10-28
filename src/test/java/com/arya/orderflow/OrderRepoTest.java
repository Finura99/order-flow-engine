package com.arya.orderflow;
import com.arya.orderflow.domain.OrderSide;
import com.arya.orderflow.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import com.arya.orderflow.domain.Order;

@DataJpaTest
class OrderRepoTest {

    @Autowired
    private OrderRepository repository;

    @BeforeEach
    void setup() {
        Order order = new Order(
                1,
                "AAPL",
                OrderSide.BUY,
                10,
                150.23
        );
    }

    @@Test
    void persistenceTest() {

        Order order = new Order(
                1,
                "AAPL",
                OrderSide.BUY,
                10,
                150.23
        );

        repository.save(order);
    }
}
