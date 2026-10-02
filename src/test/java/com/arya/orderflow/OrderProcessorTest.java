package com.arya.orderflow;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class OrderProcessorTest {

    @Test 
    void orderProcessorReturnsSuccessfulResult() {
        OrderValidator validator = new OrderValidator();

        OrderProcessor processor = new OrderProcessor(validator);

        Order order = new Order(1, "AAPL", OrderSide.BUY, 10, 190.2);

        ProcessingResult result = processor.process(order);

        assertTrue(result.isSuccessful());
        assertEquals(OrderStatus.FILLED, result.getFinalStatus());

        }

    @Test
    void orderProcessorReturnsUnsuccessfulResult() {
        OrderValidator validator = new OrderValidator();

        OrderProcessor processor = new OrderProcessor(validator);

        Order order = new Order(2, "AAPL",OrderSide.BUY, 20, -100);

        ProcessingResult result = processor.process(order);

        assertFalse(result.isSuccessful());
        assertEquals(OrderStatus.REJECTED, result.getFinalStatus());
    }

    @Test
    void orderProcessorReturnsThreeResults() {
        OrderValidator validator = new OrderValidator();

        OrderProcessor processor = new OrderProcessor(validator); // dependent on validator

        Order order1 = new Order(3, "AAPL",OrderSide.BUY, 10, 100);
        Order order2 = new Order(4, "MSFT", OrderSide.BUY, 30, -590.2);
        Order order3 = new Order(5, "TSLA", OrderSide.BUY, 20, 490.2);
        // ARRANGE all objects

        List<Order> orders = List.of(order1, order2, order3); // put these in a List<Order>

        List<ProcessingResult> results = processor.processAll(orders); // results stored in this variable

        assertEquals(3, results.size()); // ASSERT results.size() == 3
        assertEquals(OrderStatus.FILLED, results.get(0).getFinalStatus());
        assertEquals(OrderStatus.REJECTED, results.get(1).getFinalStatus());
        assertEquals(OrderStatus.FILLED, results.get(2).getFinalStatus());
    }

    
}