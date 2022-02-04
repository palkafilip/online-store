package com.fp.onlinestore.customers.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor(staticName = "of")
public class CustomerToRegister {

    private String firstName;

    private String lastName;
}
