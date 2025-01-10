package com.ecommerce.productservice.exception;

public class OutOfRangeException extends RuntimeException {

    public OutOfRangeException(String message) {
        super(message);
    }
}
