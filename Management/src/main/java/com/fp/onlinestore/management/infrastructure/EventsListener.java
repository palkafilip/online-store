package com.fp.onlinestore.management.infrastructure;

import com.fp.onlinestore.management.api.PurchasedProduct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class EventsListener {

    private final ManagementService managementService;

    @KafkaListener(topics = "products", containerFactory = "kafkaListenerContainerFactory")
    public void purchasedProductListener(final PurchasedProduct purchasedProduct) {
        log.info("Received event: " + purchasedProduct);
        managementService.savePurchasedProduct(purchasedProduct);
    }
}
