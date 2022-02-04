package com.fp.onlinestore.management.persitence;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity(name = "PURCHASED_PRODUCT")
@AllArgsConstructor(staticName = "of")
public class PurchasedProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer productId;

    private String productName;

    private Integer customerId;

    private Integer quantity;

    private Float totalPrice;

    private LocalDate purchaseDate;

    public PurchasedProductEntity(final Integer productId, final String productName, final Integer customerId,
                                  final Integer quantity, final Float totalPrice, final LocalDate purchaseDate) {
        this.productId = productId;
        this.productName = productName;
        this.customerId = customerId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.purchaseDate = purchaseDate;
    }
}
