package com.example.transactionservice.exception;

public class LimitNotFoundException extends RuntimeException {

    public LimitNotFoundException(String message) {
        super(message);
    }

}
