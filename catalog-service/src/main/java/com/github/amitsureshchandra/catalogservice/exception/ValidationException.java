package com.github.amitsureshchandra.orderservice.exception;
public class ValidationException extends RuntimeException{
    public ValidationException(String msg){
        super(msg);
    }
}
