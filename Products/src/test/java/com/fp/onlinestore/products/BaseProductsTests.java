package com.fp.onlinestore.products;

import com.fp.onlinestore.products.api.Customer;
import com.fp.onlinestore.products.infrastructure.kafka.EventsProducer;
import com.fp.onlinestore.products.infrastructure.providers.coupons.CouponsProvider;
import com.fp.onlinestore.products.infrastructure.providers.customers.CustomersProvider;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

public class BaseProductsTests {

    @MockBean
    CustomersProvider customersProvider;

    @MockBean
    CouponsProvider couponsProvider;

    @MockBean
    EventsProducer eventsProducer;

    @BeforeEach
    void setUp() {
        Mockito.when(customersProvider.getCustomer(Mockito.any()))
                .thenReturn(new Customer(1, "FIRST_NAME", "LAST_NAME"));

        Mockito.when(couponsProvider.getActiveCoupon()).thenReturn(Optional.empty());
    }
}
