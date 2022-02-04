package com.fp.onlinestore.products.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "PURCHASED_PRODUCT")
@AllArgsConstructor(staticName = "of")
public class PurchasedProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity product;

    private Integer customerId;

    private Integer quantity;

    private Float totalPrice;

    public PurchasedProductEntity(final ProductEntity product, final Integer customerId,
                                  final Integer quantity, final Float totalPrice) {
        this.product = product;
        this.customerId = customerId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
