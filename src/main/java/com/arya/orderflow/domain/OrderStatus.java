package com.arya.orderflow.domain;

public enum OrderStatus {
    RECEIVED,
    VALIDATED,
    PROCESSING,
    FILLED,
    REJECTED
}

// Order =! Trade, Order is an instruction. Trade is the action.

