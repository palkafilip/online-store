package com.fp.onlinestore.products.infrastructure.kafka;

import com.fp.onlinestore.products.api.PurchasedProduct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.fp.onlinestore.products.infrastructure.kafka.KafkaConstants.PRODUCTS_TOPIC;

@Slf4j
@AllArgsConstructor
public class KafkaEventsProducer implements EventsProducer {

    private final KafkaTemplate<String, PurchasedProduct> kafkaTemplate;


    @Override
    public void sendPurchasedProduct(final PurchasedProduct product) {
        try {
            kafkaTemplate.send(PRODUCTS_TOPIC, product);
        } catch (Exception e) {
            log.error("Could not send event");
        }
    }
}
