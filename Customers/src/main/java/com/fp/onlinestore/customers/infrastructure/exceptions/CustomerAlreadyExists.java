package com.fp.onlinestore.customers.infrastructure.exceptions;

public class CustomerAlreadyExists extends RuntimeException {

    public CustomerAlreadyExists(final String firstName, final String lastName) {
        super("Customer with firstName = " + firstName + " and lastName = " + lastName + " already exists");
    }
}
