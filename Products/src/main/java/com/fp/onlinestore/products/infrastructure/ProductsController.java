package com.fp.onlinestore.products.infrastructure;

import com.fp.onlinestore.products.api.PurchaseProductRequest;
import com.fp.onlinestore.products.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController(value = "/")
public class ProductsController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable final Integer id) {
        Product product = productService.getProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/products/{id}")
    public ResponseEntity<Object> purchaseProduct(@PathVariable final Integer id,
                                                  @RequestBody final PurchaseProductRequest request) {
        Integer customerId = request.getCustomerId();
        Integer quantity = request.getQuantity();
        productService.purchaseProduct(id, customerId, quantity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}

