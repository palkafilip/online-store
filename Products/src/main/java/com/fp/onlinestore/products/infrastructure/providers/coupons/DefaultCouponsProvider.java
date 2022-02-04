package com.fp.onlinestore.products.infrastructure.providers.coupons;

import com.fp.onlinestore.products.api.Coupon;
import com.fp.onlinestore.products.api.Coupons;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Optional;
import java.util.function.Supplier;

@Component
@AllArgsConstructor
public class DefaultCouponsProvider implements CouponsProvider {

    private final RestTemplate restTemplate;

    private final String URL = "http://coupons-app";

    @Override
    public Coupons getAllCoupons() {
        return tryExecute(() -> restTemplate.getForEntity(URL + "/coupons", Coupons.class))
               .orElse(new Coupons(Collections.emptyList()));
    }

    @Override
    public Optional<Coupon> getActiveCoupon() {
        return tryExecute(() -> restTemplate.getForEntity(URL + "/coupons/active", Coupon.class));
    }

    private <T> Optional<T> tryExecute(final Supplier<ResponseEntity<T>> supplier) {
        try {
            ResponseEntity<T> responseEntity = supplier.get();
            return Optional.ofNullable(responseEntity.getBody());
        } catch (HttpClientErrorException exception) {
            return Optional.empty();
        }
    }
}
