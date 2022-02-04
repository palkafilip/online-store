package com.fp.onlinestore.products.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasedProductRepository extends JpaRepository<PurchasedProductEntity, Integer> {
}
