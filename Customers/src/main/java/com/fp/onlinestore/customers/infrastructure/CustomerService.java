package com.fp.onlinestore.customers.infrastructure;

import com.fp.onlinestore.customers.api.CustomerToRegister;
import com.fp.onlinestore.customers.domain.Customer;
import com.fp.onlinestore.customers.infrastructure.exceptions.CustomerAlreadyExists;
import com.fp.onlinestore.customers.infrastructure.exceptions.CustomerNotFoundException;
import com.fp.onlinestore.customers.persistence.CustomerEntity;
import com.fp.onlinestore.customers.persistence.CustomerEntityMapper;
import com.fp.onlinestore.customers.persistence.CustomerRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final EmergencyRegistrator emergencyRegistrator;

    public Customer getCustomer(final Integer id) {
        try {
            CustomerEntity entity = customerRepository.getById(id);
            return CustomerEntityMapper.toCustomer(entity);
        } catch (final EntityNotFoundException exception) {
            throw new CustomerNotFoundException(id);
        }
    }

    public List<Customer> getAllCustomers() {
        List<CustomerEntity> entities = customerRepository.findAll();
        return CustomerEntityMapper.toCustomers(entities);
    }

    @HystrixCommand(fallbackMethod = "fallbackRegister", ignoreExceptions = {CustomerAlreadyExists.class})
    public void registerCustomer(final CustomerToRegister customerToRegister) {
        checkIfCustomerExists(customerToRegister);
        CustomerEntity entity = toEntity(customerToRegister);

        // random save success to simulate database connection problem (for Hystrix)
        float random = new Random().nextFloat();
        if (random < 0.5) {
            customerRepository.saveAndFlush(entity);
        } else {
            throw new RuntimeException();
        }
    }

    private void fallbackRegister(final CustomerToRegister customerToRegister) {
        log.info("fallbackRegister takes control!");
        checkIfCustomerExists(customerToRegister);
        CustomerEntity entity = toEntity(customerToRegister);
        emergencyRegistrator.emergencyRegistration(entity);
    }

    private void checkIfCustomerExists(final CustomerToRegister customerToRegister) {
        List<CustomerEntity> entities = customerRepository.findAll();
        List<Customer> customers = CustomerEntityMapper.toCustomers(entities);
        for (Customer customer : customers) {
            if (customer.getFirstName().equals(customerToRegister.getFirstName()) &&
                    customer.getLastName().equals(customerToRegister.getLastName())) {
                throw new CustomerAlreadyExists(customerToRegister.getFirstName(), customerToRegister.getLastName());
            }
        }
    }

    private CustomerEntity toEntity(final CustomerToRegister customerToRegister) {
        Customer customer = Customer.of(null, customerToRegister.getFirstName(), customerToRegister.getLastName());
        return CustomerEntityMapper.toEntity(customer);
    }

}
