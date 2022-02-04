package com.fp.onlinestore.products.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "PRODUCTS_QUANTITY")
@AllArgsConstructor(staticName = "of")
public class ProductsQuantityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity product;

    private Integer quantity;

    private Float pricePerItem;
}
