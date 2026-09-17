package com.arya.orderflow;

public class OrderProcessor {
    private final OrderValidator validator;

    public OrderProcessor(OrderValidator validator) {
        this.validator = validator;
    }

    public ProcessingResult process(Order order) {
        //Do work, this time, return a processingResult object
        //changed from void to ProcessingResult
        if (!validator.isValid(order)) {
            order.markRejected();

            return new ProcessingResult(
                    order.getId(),
                    false,
                    order.getStatus(),
                    "Order rejected by validation rules"
            );
        }

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