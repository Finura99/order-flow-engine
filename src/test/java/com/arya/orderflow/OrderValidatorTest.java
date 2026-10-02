package com.arya.orderflow;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

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
        // Make a deliberately bad order

        List<String> errors = validator.getValidationErrors(order);
        // run validation

        assertTrue(errors.isEmpty());
        // check that the expected error appears 
    }

    @Test
    void invalidQuantityReturnsValidationError() {
        OrderValidator validator = new OrderValidator();

        Order order = new Order(
            2, 
            "AAPL", 
            OrderSide.BUY, 
            -10, 
            190.50
        );

        List<String> errors = validator.getValidationErrors(order);

        assertTrue(errors.contains("Quantity must be positive"));
    }

    @Test
    void blankSymbolReturnsValidationError() {
        OrderValidator validator = new OrderValidator();

        Order order = new Order(
            3, 
            "", 
            OrderSide.BUY, 
            100, 
            170.2
        );

        List<String> errors = validator.getValidationErrors(order);

        assertTrue(errors.contains("Symbol must not be blank"));
    }

    @Test
    void zeroPriceReturnsValidationError() {
        OrderValidator validator = new OrderValidator();

        Order order = new Order(
            4, 
            "MSFT", 
            OrderSide.SELL, 
            20, 
            0
        );

        List<String> errors = validator.getValidationErrors(order);

        assertTrue(errors.contains("Price must be positive"));
    }

    @Test
    void negativePriceReturnsValidationError() {
        OrderValidator validator = new OrderValidator();

        Order order = new Order(
            4, 
            "MSFT", 
            OrderSide.SELL, 
            20, 
            -10
        );

        List<String> errors = validator.getValidationErrors(order);

        assertTrue(errors.contains("Price must be positive"));
    }

    @Test
    void multipleInvalidFieldReturnsMultipleErrors() {
        OrderValidator validator = new OrderValidator();

        Order order = new Order(
            5, 
            "", 
            OrderSide.BUY, 
            -10, 
            0
        );

        List<String> errors = validator.getValidationErrors(order);

        assertTrue(errors.contains("Symbol must not be blank"));
        assertTrue(errors.contains("Quantity must be positive"));
        assertTrue(errors.contains("Price must be positive"));
    }
}

