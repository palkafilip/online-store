package com.fp.onlinestore.products.infrastructure.providers.coupons;

import com.fp.onlinestore.products.api.Coupon;
import com.fp.onlinestore.products.api.Coupons;

import java.util.Optional;

public interface CouponsProvider {

    Coupons getAllCoupons();

    Optional<Coupon> getActiveCoupon();
}
