package com.fp.onlinestore.customers.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor(staticName = "of")
public class Customer {

    private Integer id;

    private String firstName;

    private String lastName;
}
