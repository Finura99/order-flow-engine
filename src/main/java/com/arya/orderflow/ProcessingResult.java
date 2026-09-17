package com.arya.orderflow;

public class ProcessingResult {
    private final int orderId;
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