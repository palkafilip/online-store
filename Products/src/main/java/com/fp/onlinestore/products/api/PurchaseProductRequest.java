package com.fp.onlinestore.products.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseProductRequest {

    private Integer customerId;

    private Integer quantity;
}
