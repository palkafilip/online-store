package com.fp.onlinestore.products.infrastructure.providers.customers;

import com.fp.onlinestore.products.api.Customer;

public interface CustomersProvider {

    Customer getCustomer(Integer id);
}
