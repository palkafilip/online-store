package com.fp.onlinestore.products.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private Integer id;

    private String firstName;

    private String lastName;
}
