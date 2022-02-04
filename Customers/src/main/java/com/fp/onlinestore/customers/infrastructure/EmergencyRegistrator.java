package com.fp.onlinestore.customers.infrastructure;

import com.fp.onlinestore.customers.persistence.CustomerEntity;
import com.fp.onlinestore.customers.persistence.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class EmergencyRegistrator {

    private final CustomerRepository customerRepository;

    private final List<CustomerEntity> newCustomersToSave = new ArrayList<>();

    public void emergencyRegistration(final CustomerEntity newCustomer) {
        newCustomersToSave.add(newCustomer);
    }

    @Scheduled(fixedDelay = 60000)
    private void registrationScheduler() {
        log.info("Registration Scheduler fired!");
        if (newCustomersToSave.size() > 0) {
            Iterator<CustomerEntity> iterator = newCustomersToSave.iterator();
            while (iterator.hasNext()) {
                try {
                    CustomerEntity newCustomer = iterator.next();
                    log.info("Scheduler tries to create new customer");
                    customerRepository.saveAndFlush(newCustomer);
                    iterator.remove();
                    log.info("Scheduler has created new customer successfully");
                } catch (Exception ignored) {
                    log.error("An error has occurred while saving new customer");
                }
            }
        }
    }

}
