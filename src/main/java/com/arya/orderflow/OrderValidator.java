package com.arya.orderflow;

import java.util.ArrayList;
import java.util.List;

public class OrderValidator {

    // public boolean isValid(Order order) {
    //     return order.getQuantity() > 0 
    //     && !order.getSymbol().isBlank()
    //     && order.getPrice() > 0;
    // // whole thing here is a method
    // }

    public List<String> getValidationErrors(Order order) {
        List<String> errors = new ArrayList<>();

        if (order.getQuantity() <= 0) {
            errors.add("Quantity must be positive");
        }

        if (order.getSymbol().isBlank()) {
            errors.add("Symbol must not be blank");
        }

        if (order.getPrice() <= 0) {
            errors.add("Price must be positive");
        }
        
        return errors;
        //validation rules live here
    }

    public boolean isValid(Order order) {
        return getValidationErrors(order).isEmpty();
        //Checks if the lsit contains zero items
    }
}

//business rules checker

