package com.fp.onlinestore.customers.infrastructure;

import com.fp.onlinestore.customers.api.CustomerToRegister;
import com.fp.onlinestore.customers.domain.Customer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@AllArgsConstructor
@RestController(value = "/")
public class CustomersController {

    private final CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable final Integer id) {
        Customer customer = customerService.getCustomer(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<Object> registerCustomer(@RequestBody final CustomerToRegister customerToRegister) {
        customerService.registerCustomer(customerToRegister);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
