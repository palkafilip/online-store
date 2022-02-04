package com.fp.onlinestore.products.infrastructure.kafka.config;

import com.fp.onlinestore.products.api.PurchasedProduct;
import com.fp.onlinestore.products.infrastructure.kafka.KafkaEventsProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public KafkaEventsProducer kafkaEventsProducer(final KafkaTemplate<String, PurchasedProduct> kafkaTemplate) {
        return new KafkaEventsProducer(kafkaTemplate);
    }
}
