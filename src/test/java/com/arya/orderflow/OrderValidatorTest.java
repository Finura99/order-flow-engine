package com.arya.orderflow;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderValidatorTest {

    @Test
    void validOrderHasNoValidationErrors() {

        OrderValidator validator = new OrderValidator();

        Order order = new Order(
                1,
                "AAPL",
                OrderSide.BUY,
                100,
                190.50
        );

        List<String> errors = validator.getValidationErrors(order);

        assertTrue(errors.isEmpty());
    }
}

