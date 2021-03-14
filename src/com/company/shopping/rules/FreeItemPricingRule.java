package com.company.shopping.rules;

import com.company.shopping.model.ShoppingCart;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This price rules set target sku price to zero when one purchase product with sku
 */
public class FreeItemPricingRule implements PricingRule {
    private String sku;
    private String targetSku;

    public FreeItemPricingRule(String sku, String targetSku) {
        assert sku != null : "sku cannot be null";
        assert targetSku != null : "target sku cannot be null";

        this.sku = sku;
        this.targetSku = targetSku;
    }

    @Override
    public void apply(ShoppingCart cart) {
        if (cart.isEmpty()) return;

        int count = (int) cart.getItems()
                .stream()
                .filter(item -> sku.equals(item.getProduct().getSku()))
                .count();

        if (count == 0) return;

        AtomicInteger counter = new AtomicInteger(count);
        cart.getItems()
                .stream()
                .filter(item -> targetSku.equals(item.getProduct().getSku()))
                .forEach(item -> {
                    if (counter.getAndDecrement() > 0) {
                        item.setPrice(BigDecimal.ZERO);
                    }
                });
    }

}
