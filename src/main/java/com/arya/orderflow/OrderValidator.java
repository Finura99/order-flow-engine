package com.arya.orderflow;

public class OrderValidator {

    // public boolean isValid(Order order) {
    //     return order.getQuantity() > 0 
    //     && !order.getSymbol().isBlank()
    //     && order.getPrice() > 0;
    // // whole thing here is a method
    // }

    public boolean isValid(Order order) {
    if (order.getQuantity() <= 0) {
        return false;
    }

    if (order.getSymbol().isBlank()) {
        return false;
    }

    if (order.getPrice() <= 0) {
        return false;
    }

    return true;
    
    } 
    // easier to read the business rules (10/09/26) and will refactor it later...
}

//business rules checkerisValid
