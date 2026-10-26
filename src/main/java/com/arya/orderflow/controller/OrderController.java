package com.arya.orderflow.controller;


import com.arya.orderflow.domain.Order;
import com.arya.orderflow.domain.OrderStatus;
import com.arya.orderflow.service.OrderProcessor;
import com.arya.orderflow.domain.ProcessingResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class OrderController {


    private final OrderProcessor processor;
    // receive the field it needs for DI

    public OrderController(OrderProcessor processor) {
        this.processor = processor;
    }
    // constructor injection achieving DI -> Ioc



    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    @PostMapping("/orders")
    public ResponseEntity<ProcessingResult> orders(@RequestBody Order order) {

        ProcessingResult result = processor.process(order);
        // Business logic

        if (result.isSuccessful()) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);

        // HTTP status + ProcessingResult body
    }
    

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> orderId(@PathVariable int id) {
        //Path var means take the url path and bind it to the above java param

        Order storedOrder = processor.findOrderId(id);
        // asks/calls the service layer

        if (storedOrder == null) {
            return ResponseEntity.notFound().build();
        }
        // order missing would give back 404 error

        return ResponseEntity.ok(storedOrder);
        // 200 + JSON Body

    }

    // route path
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrderStatus(@RequestParam OrderStatus status) {
        // ?status=FILLED is the query param

        List<Order> orderStatusList = processor.findOrderStatus(status);

        return ResponseEntity.ok(orderStatusList);

    }
}



// Rest controller tells spring that this handles HTTP requests
// get/post/put mapping is the specific annotation to the method
// public string health is the controller method
// return is the response body
