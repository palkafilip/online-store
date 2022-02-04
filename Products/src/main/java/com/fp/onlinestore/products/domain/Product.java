package com.fp.onlinestore.products.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor(staticName = "of")
public class Product {

    private Integer id;

    private String name;

    private String category;

    private String description;

    private Integer quantity;

    private Float pricePerItem;
}
