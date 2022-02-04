package com.fp.onlinestore.products.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity(name = "PRODUCT")
@AllArgsConstructor(staticName = "of")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String category;

    private String description;

    @OneToOne(mappedBy = "product")
    private ProductsQuantityEntity productsQuantity;

    @OneToOne(mappedBy = "product")
    private PurchasedProductEntity purchasedProduct;
}
