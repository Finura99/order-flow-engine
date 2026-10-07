package com.arya.orderflow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class OrderProcessor {
    private final OrderValidator validator;


    private final ExecutorService executor = Executors.newFixedThreadPool(3);
    // creates a pool with exactly 3 worker threads



    // methods

    public OrderProcessor(OrderValidator validator) {
        this.validator = validator;
    } // constructor dependency injection
    // constructor is a door for dependencies

    public ProcessingResult process(Order order) {
        // Do work, this time, return a processingResult object
        // changed from void to ProcessingResult

        if (order == null) {
            throw new IllegalArgumentException("Order must not be null");
        } // check null

        List<String> validationErrors = validator.getValidationErrors(order);

        if (!validator.isValid(order)) {
            order.markRejected(); // state of the order

            return new ProcessingResult(
                    order.getId(),
                    false,
                    order.getStatus(),
                    String.join(", ", validationErrors)
            ); // return new because every order needs its own reciept...
        }


        // Otherwise...

        order.markValidated();
        order.markProcessing();
        order.markFilled();

        return new ProcessingResult(
                order.getId(),
                true,
                order.getStatus(),
                "Order processed successfully"
        );
    }

    public List<ProcessingResult> processAll(List<Order> orders) {
        List<ProcessingResult> results = new ArrayList<>(); // empty output list

        // method named processAll that takes in a list of orders and returns a list of ProcessingResults

        for (Order order : orders) {
            System.out.println(order);
            results.add(process(order)); 
            // process one order and add it in the results List (validating them)
        }
        
        return results; // returns one ProcessingResult
        // structure is access domain, input type, name , output type
    }
}