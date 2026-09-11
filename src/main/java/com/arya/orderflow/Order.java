package com.arya.orderflow;

public class Order { 
// // This class is public so it can be used by other parts of the application.

    private final int id;
    private final String symbol;
    private final OrderSide side;
    private final int quantity;
    private final double price; 
    // // Using double for now while learning. In a real financial system, BigDecimal would avoid floating-point precision issues.
    private OrderStatus status;

    public Order(int id, String symbol, OrderSide side, int quantity, double price){
        this.id = id;
        this.symbol = symbol;
        this.side = side;
        this.quantity = quantity;
        this.price = price;
        this.status = OrderStatus.RECEIVED; // Set onto its default state.
    }

    // Constructor in java

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

    // getters in java for grabbing the private fields without modifiying them...

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
    } // override changes the default tostring method thats derives from the object class

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

