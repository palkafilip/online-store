package com.fp.onlinestore.products.infrastructure.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(final Integer id) {
        super("Product with id = [" + id + "] was not found");
    }
}
