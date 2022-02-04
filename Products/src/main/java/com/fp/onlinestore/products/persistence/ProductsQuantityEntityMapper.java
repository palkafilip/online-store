package com.fp.onlinestore.products.persistence;

import com.fp.onlinestore.products.domain.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductsQuantityEntityMapper {

    public static List<Product> toProducts(final List<ProductsQuantityEntity> entities) {
        return entities.stream().map(ProductsQuantityEntityMapper::toProduct).collect(Collectors.toList());
    }

    public static Product toProduct(final ProductsQuantityEntity entity) {
        ProductEntity productEntity = entity.getProduct();
        return Product.of(productEntity.getId(), productEntity.getName(), productEntity.getCategory(),
                productEntity.getDescription(), entity.getQuantity(), entity.getPricePerItem());
    }
}
