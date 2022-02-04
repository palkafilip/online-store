package com.fp.onlinestore.management.persitence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasedProductRepository extends JpaRepository<PurchasedProductEntity, Integer> {

}
