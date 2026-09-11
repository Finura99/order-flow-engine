package com.arya.orderflow;

public class OrderProcessor { //must match class file name...
    private final OrderValidator validator;
    // created a field from type OrderValidator
    // orderproessor depends on ordervalidator

    public OrderProcessor(OrderValidator validator) {
        this.validator = validator;
    } // constructor
    // instead of creating order validator, we inject it through the constructor

    public void process(Order order) {
        if (!validator.isValid(order)) {
            order.markRejected(); // change status of the order
            return;
        }

        order.markValidated();
        order.markProcessing();
        order.markFilled();

        // methods to process the order through the happy path
        // invalid order → REJECTED
        // valid order → VALIDATED → PROCESSING → FILLED
    }
}

// composition + dependency injection applied in this class.