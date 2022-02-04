package com.fp.onlinestore.customers.persistence;

import com.fp.onlinestore.customers.domain.Customer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerEntityMapper {

    public static List<Customer> toCustomers(final List<CustomerEntity> entities) {
        return entities.stream().map(CustomerEntityMapper::toCustomer).collect(Collectors.toList());
    }

    public static Customer toCustomer(final CustomerEntity entity) {
        return Customer.of(entity.getId(), entity.getFirstName(), entity.getLastName());
    }

    public static CustomerEntity toEntity(final Customer customer) {
        return new CustomerEntity(customer.getFirstName(), customer.getLastName());
    }
}
