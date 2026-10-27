package com.arya.orderflow;

import com.arya.orderflow.controller.OrderController;
import com.arya.orderflow.domain.Order;
import com.arya.orderflow.domain.OrderSide;
import com.arya.orderflow.domain.OrderStatus;
import com.arya.orderflow.service.OrderProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class OrderControllerTest {

    private MockMvc mockMvc;
    private OrderProcessor processor;
    private OrderController controller;

    @BeforeEach
    void setup() {

        processor = mock(OrderProcessor.class);
        // fake service

        controller = new OrderController(processor);
        // real controller

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        // build a fake MVC/HTTP environment around that controller
       
    }




    @Test
    void getOrderByIdReturns200Test() throws Exception {

        Order order = new Order(
                1,
                "AAPL",
                OrderSide.BUY,
                10,
                150.60
        );

        //Arrange
        when(processor.findOrderId(1)).thenReturn(order);
        // This is a stub

        //Assert
        mockMvc.perform(get("/orders/1"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.id").value(1),
                        jsonPath("$.symbol").value("AAPL"),
                        jsonPath("$.quantity").value(10));

        // $ = root JSON object
        // .id = field called id

    }

    @Test
    void getOrderByIdReturns404Test() throws Exception {

        //Arrange
        when(processor.findOrderId(999)).thenReturn(null);

        //Act
        mockMvc.perform(get("/orders/999"))
                .andExpect(status().isNotFound());
    }
    // any methods using .andexpect() is the ASSERT


    @Test
    void getOrderByStatusFilledTest() throws Exception {

        //ARRANGE
        Order order = new Order(
                2,
                "NVDA",
                OrderSide.BUY,
                57,
                216.00
        );

        Order order2 = new Order(
                3,
                "GOOG",
                OrderSide.BUY,
                5,
                314.00
        );

        order.markValidated();
        order.markProcessing();
        order.markFilled();

        order2.markValidated();
        order2.markProcessing();
        order2.markFilled();

        List<Order> testList = List.of(order, order2);

        when(processor.findOrderStatus(OrderStatus.FILLED)).thenReturn(testList);


        //ACT & ASSERT
        mockMvc.perform(get("/orders?status=FILLED"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$[0].symbol").value("NVDA"),
                        jsonPath("$[1].symbol").value("GOOG"),
                        jsonPath("$[0].status").value("FILLED"));
    }

    @Test
    void getOrdersByStatusReturnsEmptyListWhenNoMatchesTest() throws Exception {

        //No need for Arrange as there are zero matching rejected orders, hence no need of an order obj
        //Stubbing
        when(processor.findOrderStatus(OrderStatus.REJECTED))
                .thenReturn(List.of());

        //ACT and ARRANGE
        mockMvc.perform(get("/orders?status=REJECTED"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$").isArray(),
                        jsonPath("$").isEmpty()
                );
        //this test essentially checks if it returns a 200 response with the empty array
    }
}

// unit tests should have mocked dependencies
// integration should wire more real components
