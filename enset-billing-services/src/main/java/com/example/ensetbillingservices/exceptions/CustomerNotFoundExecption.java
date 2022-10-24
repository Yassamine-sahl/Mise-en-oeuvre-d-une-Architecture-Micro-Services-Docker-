package com.example.ensetbillingservices.exceptions;

public class CustomerNotFoundExecption extends RuntimeException {
    public CustomerNotFoundExecption(String messages) {
        super(messages);
    }
}
