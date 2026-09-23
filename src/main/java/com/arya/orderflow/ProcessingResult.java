package com.arya.orderflow;

public class ProcessingResult {
    private final int orderId; //create a public method especially when the variable is private
    private final boolean successful;
    private final OrderStatus finalStatus;
    private final String message;

    public ProcessingResult(int orderId, boolean successful, OrderStatus finalStatus, String messege) {
        this.orderId = orderId;
        this.successful = successful;
        this.finalStatus = finalStatus;
        this.message = messege;
    }

    public int getOrderId() {
        return orderId;
    }

    public boolean successful() {
        return successful;
    }

    public OrderStatus finalStatus() {
        return finalStatus;
    }

    public String messege() {
        return message;
    }

    // variable/fields, constructor for those variables, method for behaviours = object thts used elsewhere...

    @Override
    public String toString() {
        return "ProcessingResult{" +
                "orderId=" + orderId +
                ", successful=" + successful +
                ", finalStatus=" + finalStatus +
                ", message='" + message + '\'' +
                '}';
    }

    // not the order itself, its the reciept
}