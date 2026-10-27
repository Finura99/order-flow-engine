package com.arya.orderflow;

import com.arya.orderflow.domain.*;
import com.arya.orderflow.repository.OrderRepository;
import com.arya.orderflow.service.OrderProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderProcessorTest {

    private OrderValidator validator;
    private OrderRepository repository;
    private OrderProcessor processor;

    @BeforeEach
    void setUp() {
        validator = new OrderValidator();
        repository = mock(OrderRepository.class);
        processor = new OrderProcessor(validator, repository);
    }

    @Test
    void validOrderIsProcessedSuccessfully() {

        Order order = new Order(
                1,
                "AAPL",
                OrderSide.BUY,
                10,
                150.60
        );

        ProcessingResult result = processor.process(order);

        assertTrue(result.isSuccessful());
        assertEquals(OrderStatus.FILLED, result.getFinalStatus());

        verify(repository).save(order);
    }

    @Test
    void invalidOrderIsRejected() {

        Order order = new Order(
                2,
                "AAPL",
                OrderSide.BUY,
                0,
                150.60
        );

        ProcessingResult result = processor.process(order);

        assertFalse(result.isSuccessful());
        assertEquals(OrderStatus.REJECTED, result.getFinalStatus());

        verify(repository).save(order);
    }

    @Test
    void findOrderByIdReturnsOrderWhenPresent() {

        Order order = new Order(
                3,
                "MSFT",
                OrderSide.BUY,
                20,
                320.00
        );

        when(repository.findById(3))
                .thenReturn(Optional.of(order));

        Order result = processor.findOrderId(3);

        assertEquals(order, result);
    }

    @Test
    void findOrderByIdReturnsNullWhenMissing() {

        when(repository.findById(999))
                .thenReturn(Optional.empty());

        Order result = processor.findOrderId(999);

        assertNull(result);
    }

    @Test
    void findOrdersByStatusReturnsMatchingOrders() {

        Order order1 = new Order(
                1,
                "AAPL",
                OrderSide.BUY,
                10,
                150.00
        );

        Order order2 = new Order(
                2,
                "MSFT",
                OrderSide.BUY,
                20,
                320.00
        );

        when(repository.findByStatus(OrderStatus.FILLED))
                .thenReturn(List.of(order1, order2));

        List<Order> result =
                processor.findOrderStatus(OrderStatus.FILLED);

        assertEquals(2, result.size());
        assertTrue(result.contains(order1));
        assertTrue(result.contains(order2));
    }
}