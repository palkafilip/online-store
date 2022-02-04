package com.fp.onlinestore.products.infrastructure.exceptions;

public class RequiredQuantityNotAvailableException extends RuntimeException {

    public RequiredQuantityNotAvailableException(final Integer requiredQuantity,
                                                 final Integer availableQuantity,
                                                 final Integer productId) {
        super("Required quantity = [" + requiredQuantity + "] is bigger than available" +
                " quantity = [" + availableQuantity + "] for product with id = [" + productId + "]");
    }
}
