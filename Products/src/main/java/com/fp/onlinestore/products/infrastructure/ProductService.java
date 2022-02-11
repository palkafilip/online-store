package com.fp.onlinestore.products.infrastructure;

import com.fp.onlinestore.products.api.Coupon;
import com.fp.onlinestore.products.api.PurchasedProduct;
import com.fp.onlinestore.products.domain.Product;
import com.fp.onlinestore.products.infrastructure.exceptions.CustomerNotFoundException;
import com.fp.onlinestore.products.infrastructure.exceptions.ProductNotFoundException;
import com.fp.onlinestore.products.infrastructure.exceptions.RequiredQuantityNotAvailableException;
import com.fp.onlinestore.products.infrastructure.kafka.EventsProducer;
import com.fp.onlinestore.products.infrastructure.providers.coupons.CouponsProvider;
import com.fp.onlinestore.products.infrastructure.providers.customers.CustomersProvider;
import com.fp.onlinestore.products.persistence.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductsQuantityRepository productsQuantityRepository;

    private final PurchasedProductRepository purchasedProductRepository;

    private final CustomersProvider customersProvider;

    private final CouponsProvider couponsProvider;

    private final EventsProducer eventsProducer;

    public List<Product> getAllProducts() {
        List<ProductsQuantityEntity> entities = productsQuantityRepository.findAll();
        return ProductsQuantityEntityMapper.toProducts(entities);
    }

    public Product getProduct(final Integer id) {
        ProductsQuantityEntity entity = getProductsQuantityEntityById(id);
        return ProductsQuantityEntityMapper.toProduct(entity);
    }

    public void purchaseProduct(final Integer productId, final Integer customerId, final Integer quantity) {
        ProductsQuantityEntity entity = getProductsQuantityEntityById(productId);
        checkAvailableQuantity(entity, quantity);
        checkIfCustomerExists(customerId);
        Float totalPrice = calculateTotalPrice(entity.getPricePerItem(), quantity);
        PurchasedProductEntity purchasedProductEntity = new PurchasedProductEntity(entity.getProduct(), customerId, quantity, totalPrice);
        purchasedProductRepository.saveAndFlush(purchasedProductEntity);
        updateQuantityInfo(entity, quantity);
        createSummaryEvent(purchasedProductEntity);
    }

    private void updateQuantityInfo(final ProductsQuantityEntity entity, final Integer purchasedQuantity) {
        Integer quantityRemaining = entity.getQuantity() - purchasedQuantity;
        ProductsQuantityEntity updatedEntity = ProductsQuantityEntity.of(entity.getId(), entity.getProduct(), quantityRemaining, entity.getPricePerItem());
        productsQuantityRepository.saveAndFlush(updatedEntity);
    }

    private ProductsQuantityEntity getProductsQuantityEntityById(final Integer id) {
        return productsQuantityRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    private void checkAvailableQuantity(final ProductsQuantityEntity entity, final Integer requiredQuantity) {
        Integer availableQuantity = entity.getQuantity();
        if (availableQuantity < requiredQuantity) {
            Integer id = entity.getProduct().getId();
            throw new RequiredQuantityNotAvailableException(requiredQuantity, availableQuantity, id);
        }
    }

    private void checkIfCustomerExists(final Integer customerId) {
        Optional.ofNullable(customersProvider.getCustomer(customerId))
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    private Float calculateTotalPrice(final Float pricePerItem, final Integer quantity) {
        Float totalPrice = pricePerItem * quantity;
        Integer discountPercentage = couponsProvider.getActiveCoupon().map(Coupon::getDiscountPercent).orElse(0);
        if (discountPercentage != 0) {
            log.info(discountPercentage + "% discount was applied");
        }
        Float discount = (totalPrice * discountPercentage) / 100;

        return totalPrice - discount;
    }

    private void createSummaryEvent(final PurchasedProductEntity entity) {
        Integer productId = entity.getProduct().getId();
        String productName = entity.getProduct().getName();
        Integer customerId = entity.getCustomerId();
        Integer quantity = entity.getQuantity();
        Float totalPrice = entity.getTotalPrice();
        LocalDate purchaseDate = LocalDate.now();
        PurchasedProduct product =
                PurchasedProduct.of(productId, productName, customerId, quantity, totalPrice, purchaseDate);
        eventsProducer.sendPurchasedProduct(product);
    }

}
