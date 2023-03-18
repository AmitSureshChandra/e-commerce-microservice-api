package com.github.amitsureshchandra.orderservice.exception;

public class OrderProcessingException extends RuntimeException {
    public OrderProcessingException(String msg) {
        super(msg);
    }
}
