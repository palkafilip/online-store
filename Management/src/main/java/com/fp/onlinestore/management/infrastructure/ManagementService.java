package com.fp.onlinestore.management.infrastructure;

import com.fp.onlinestore.management.api.PurchasedProduct;
import com.fp.onlinestore.management.persitence.PurchasedProductEntity;
import com.fp.onlinestore.management.persitence.PurchasedProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ManagementService {

    private final PurchasedProductRepository purchasedProductRepository;

    public void savePurchasedProduct(final PurchasedProduct purchasedProduct) {
        PurchasedProductEntity entity = toPurchasedProductEntity(purchasedProduct);
        purchasedProductRepository.save(entity);

    }

    private PurchasedProductEntity toPurchasedProductEntity(final PurchasedProduct purchasedProduct) {
        return new PurchasedProductEntity(purchasedProduct.getProductId(), purchasedProduct.getProductName(),
                purchasedProduct.getCustomerId(), purchasedProduct.getQuantity(), purchasedProduct.getTotalPrice(),
                purchasedProduct.getPurchaseDate());
    }
}
