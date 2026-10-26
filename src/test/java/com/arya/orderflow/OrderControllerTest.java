package com.arya.orderflow;

import com.arya.orderflow.controller.OrderController;
import com.arya.orderflow.domain.Order;
import com.arya.orderflow.domain.OrderSide;
import com.arya.orderflow.service.OrderProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        OrderProcessor processor = mock(OrderProcessor.class);
        // fake service

        OrderController controller = new OrderController(processor);
        // real controller

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        // build a fake MVC/HTTP environment around that controller

        Order order = new Order(
                1,
                "AAPL",
                OrderSide.BUY,
                10,
                150.60
        );

        when(processor.findOrderId(1)).thenReturn(order);
       
    }

    @Test
    void getOrderByIdReturnsOrder() throws Exception {
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
    void getOrderByIdReturns404() throws Exception {
        mockMvc.perform(get("/orders/999"))
                .andExpect(status().isNotFound());
    }
}

// unit tests should have mocked dependencies
// integration should wire more real components
