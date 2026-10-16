package com.arya.orderflow;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;


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

}




// Rest controller tells spring that this handles HTTP requests
// get mapping is the specific annotation to the method
// public string health is the controller method
// return is the response body
