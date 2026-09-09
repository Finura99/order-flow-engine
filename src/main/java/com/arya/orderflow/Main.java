package com.arya.orderflow;


public class Main {
    public static void main(String[] args) {
        System.out.println("Order FLow Engine started");

        Order order = new Order(1, "AAPL", OrderSide.BUY, 100, 100.50);

        System.out.println("Order ID: " + order.getId());
        System.out.println("Symbol: " + order.getSymbol());
        System.out.println("Side: " + order.getOrderSide());
        System.out.println("Quantity: " + order.getQuantity());
        System.out.println("Price: " + order.getPrice());
        System.out.println("Status: " + order.getStatus());
    }
}


