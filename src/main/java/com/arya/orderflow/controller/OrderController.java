package com.arya.orderflow.controller;


import com.arya.orderflow.domain.Order;
import com.arya.orderflow.service.OrderProcessor;
import com.arya.orderflow.domain.ProcessingResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public String getOrderById(@PathVariable int id) {
        //Path var means take the url path and bind it to the above java param

        return "Requested Order: " + id;
    }

    @GetMapping("/orders")
    public String getOrderStatus(@RequestParam String status) {
        return "Requested Status: " + status;
    }

}




// Rest controller tells spring that this handles HTTP requests
// get mapping is the specific annotation to the method
// public string health is the controller method
// return is the response body
