package com.arya.orderflow.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Order {
// // This class is public so it can be used by other parts of the application.

    @Id
    private int id;

    private String symbol;
    private OrderSide side;
    private int quantity;
    private double price;
    // // Using double for now while learning. In a real financial system, BigDecimal would avoid floating-point precision issues.
    private OrderStatus status;

    protected Order() {
        // for JPA/Hibernate
    }

    public Order(int id, String symbol, OrderSide side, int quantity, double price){
        this.id = id;
        this.symbol = symbol;
        this.side = side;
        this.quantity = quantity;
        this.price = price;
        this.status = OrderStatus.RECEIVED; // Set onto its default state.
    }
    // Constructor in java





    //getters and setters

    public int getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public OrderSide getOrderSide() {
        return side;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public OrderStatus getStatus() {
        return status;
    }

    // getters in java for grabbing the private fields without modifying them...

  @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", side=" + side +
                ", quantity=" + quantity +
                ", price=" + price +
                ", status=" + status +
                '}';
    } // override changes the default tostring method that's derives from the object class

    public void markValidated() {
        this.status = OrderStatus.VALIDATED;
    } 
   

    public void markProcessing() {
        this.status = OrderStatus.PROCESSING;
    } 
   

    public void markFilled() {
        this.status = OrderStatus.FILLED;
    }
    

    public void markRejected() {
        this.status = OrderStatus.REJECTED;
    } 

    // methods for controlling the status of orders.

}

