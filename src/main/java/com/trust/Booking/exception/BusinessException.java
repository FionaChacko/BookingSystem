package com.trust.Booking.exception;

public class BusinessException extends RuntimeException{

    String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
