package com.fp.onlinestore.products.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsQuantityRepository extends JpaRepository<ProductsQuantityEntity, Integer> {
}
