package com.bank.api;

public class CustomerAlreadyExistsException extends Exception {

    CustomerAlreadyExistsException(String email){
        super("Customer with email "+email+" exists!");
    }
}
