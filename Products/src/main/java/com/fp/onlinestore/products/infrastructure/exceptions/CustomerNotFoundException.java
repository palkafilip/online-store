package com.fp.onlinestore.products.infrastructure.exceptions;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(final Integer customerId) {
        super("Customer with id = [" + customerId + "] was not found");
    }
}
