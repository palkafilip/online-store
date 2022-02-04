package com.fp.onlinestore.management.infrastructure.kafka;

import com.fp.onlinestore.management.api.PurchasedProduct;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {

    @Value(value = "${kafka.bootstrapAddress:localhost:9092}")
    private String bootstrapAddress;

    @Bean
    public ConsumerFactory<String, PurchasedProduct> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "products_group");
        JsonDeserializer<PurchasedProduct> deserializer = new JsonDeserializer<>(PurchasedProduct.class, false);
        deserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PurchasedProduct> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PurchasedProduct> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
