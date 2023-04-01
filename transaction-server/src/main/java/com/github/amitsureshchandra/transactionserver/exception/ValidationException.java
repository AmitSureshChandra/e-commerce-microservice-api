package com.github.amitsureshchandra.transactionserver.exception;
public class ValidationException extends RuntimeException{
    public ValidationException(String msg){
        super(msg);
    }
}
