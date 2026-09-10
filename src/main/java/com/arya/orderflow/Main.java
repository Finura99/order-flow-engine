package com.arya.orderflow;


public class Main {
    public static void main(String[] args) {
        System.out.println("Order FLow Engine started");

        Order order = new Order(1, "AAPL", OrderSide.BUY, 100, 100.50);
        // Create the trading ticket

        OrderValidator validator = new OrderValidator();

        Order validOrder = new Order(1, "AAPL", OrderSide.BUY, 100, 190.50);
        boolean validOrderResult = validator.isValid(validOrder);
        // Checks if its valid (Defensive programming)

        System.out.println("Valid Order result: " + validOrderResult);

        Order badOrder = new Order(1, "", OrderSide.BUY, -100, 190.50);
        boolean badOrderResult = validator.isValid(badOrder);
        // Asks if the order is valid

        System.out.println("Bad order result: " + badOrderResult);
        
    }
}

// For now, main acts as a mini-orchestrator

