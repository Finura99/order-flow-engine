package com.arya.orderflow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;


public class OrderProcessor {
    private final OrderValidator validator;


    private final ExecutorService executor = Executors.newFixedThreadPool(3);
    // creates a pool with exactly 3 worker threads

    private final BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();


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
        // Validate once - get list of errors

        if (!validationErrors.isEmpty()) { // if list has something, reject
            order.markRejected(); 
            // state of the order

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
            // System.out.println(order);

            results.add(process(order)); 
            // process one order and add it in the results List (validating them)
        }
        
        return results; // returns full list of Processing Results
        // structure is access domain, input type, name , output type
    }


    public Future<ProcessingResult> submitOrder (Order order) {
        return executor.submit(() -> process(order));
        // submit is the func the executorservice we use for work
        // returns a future list called processing result and uses the executor (workers) submit function to process the order...
        
    }
    
    //     // Order comes in
    //     ↓
    //     submitOrder(order)
    //     ↓
    // executor.submit(...)
    //     ↓
    // executor picks a free worker
    //     ↓
    // worker runs process(order)

    
    public void enqueueOrder(Order order) throws InterruptedException {
        
        orderQueue.put(order);
        
        // puts orders IN the blocking queue
        
    }

    
    public Order takeNextOrder(Order order) throws InterruptedException {
        
        return orderQueue.take();
        
        // takes next order OUT of the queue
        
    }
    
    public void startWorker() {
        executor.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Order order = orderQueue.take(); 
                    // if orderqueue is empty, it will stop the worker and we throw an exception
                    
                    process(order); // business logic / heart
                    
                    // wakes when order arrives, processes it, goes back to waiting
                    
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    // "okay i was interrupted while waiting, mark me as interrupted again"
                }
            }
        });
        
        // we give executor a long runnning job, that job keeps looping, wait for next order
        // process it, go back and wait again.
        // not interrupted = keep working, interrupted = someone has asked this thread to stop
    }


    
    public void shutdown() {
        executor.shutdownNow();
        // interrupts worker thread
        // InterruptedException gets caught
        // worker stops
    }



    // close the worker threads being used
    // lifecycle of the workers
    //     startWorker()
    //     ↓
    // worker waits for Orders
    //     ↓
    // Order arrives
    //     ↓
    // worker processes it
    //     ↓
    // worker waits again
    //     ↓
    // shutdown()
    //     ↓
    // shutdownNow()
    //     ↓
    // worker gets interrupted
    //     ↓
    // InterruptedException caught
    //     ↓
    // interrupt flag restored
    //     ↓
    // while condition becomes false
    // ↓
    // worker exits

}