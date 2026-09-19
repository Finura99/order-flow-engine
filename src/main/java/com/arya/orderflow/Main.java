package com.arya.orderflow;


public class Main {
    public static void main(String[] args) {
        System.out.println("Order FLow Engine started");

        OrderValidator validator = new OrderValidator();
        OrderProcessor processor = new OrderProcessor(validator);

        Order validOrder = new Order(1, "AAPL", OrderSide.BUY, 100, 190.50);
        Order badOrder = new Order(2, "", OrderSide.BUY, -100, 190.50);
        // create obj, then 

        ProcessingResult validResult = processor.process(validOrder);
        ProcessingResult badResult = processor.process(badOrder);

        System.out.println(validResult);
        System.out.println(badResult);
    }
}

// For now, main acts as a mini-orchestrator


