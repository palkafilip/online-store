package com.fp.onlinestore.management.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class PurchasedProduct implements Serializable {

    private Integer productId;

    private String productName;

    private Integer customerId;

    private Integer quantity;

    private Float totalPrice;

    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDate purchaseDate;
}
