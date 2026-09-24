package com.arya.orderflow;

import java.util.List;
public class OrderProcessor {
    private final OrderValidator validator;

    public OrderProcessor(OrderValidator validator) {
        this.validator = validator;
    } // composition ? 

    public ProcessingResult process(Order order) {
        // Do work, this time, return a processingResult object
        // changed from void to ProcessingResult
        List<String> validationErrors = validator.getValidationErrors(order);

        if (!validator.isValid(order)) {
            order.markRejected(); // state of the order

            return new ProcessingResult(
                    order.getId(),
                    false,
                    order.getStatus(),
                    String.join(", ", validationErrors)
            ); // return this user defined obj we made
        }


        // Otherwise...

        order.markValidated();
        order.markProcessing();
        order.markFilled();

        return new ProcessingResult(
                order.getId(),
                true,
                order.getStatus(),
                "Order processed successfully"
        );
    }
}