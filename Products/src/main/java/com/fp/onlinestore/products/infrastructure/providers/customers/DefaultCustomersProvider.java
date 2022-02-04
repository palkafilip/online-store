package com.fp.onlinestore.products.infrastructure.providers.customers;

import com.fp.onlinestore.products.api.Customer;
import com.fp.onlinestore.products.infrastructure.exceptions.CustomerNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class DefaultCustomersProvider implements CustomersProvider {

    private final RestTemplate restTemplate;

    @Override
    public Customer getCustomer(final Integer id) {
        try {
            ResponseEntity<Customer> responseEntity = restTemplate.getForEntity("http://customers-app/customers/" + id, Customer.class);
            Customer body = responseEntity.getBody();
            return body;
        } catch (HttpClientErrorException exception) {
            throw new CustomerNotFoundException(id);
        }
    }
}
