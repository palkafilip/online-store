package com.fp.onlinestore.products.infrastructure.kafka;

import com.fp.onlinestore.products.api.PurchasedProduct;

public interface EventsProducer {

    void sendPurchasedProduct(final PurchasedProduct product);
}
