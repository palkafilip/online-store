package com.fp.onlinestore.products.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

    private Integer id;

    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDate discountDateEnd;

    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDate discountDateStart;

    // TODO: Zmiana na percentage
    private Integer discountPercent;
}
