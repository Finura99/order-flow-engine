package com.arya.orderflow;

public enum OrderStatus {
    RECEIVED,
    VALIDATED,
    PROCESSING,
    FILLED,
    REJECTED
}

// Order =! Trade, Order is an instruction. Trade is the action.

