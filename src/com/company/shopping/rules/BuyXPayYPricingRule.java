package com.company.shopping.rules;


import com.company.shopping.model.ShoppingCart;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This is the rule for buy X item at price of Y, where X >Y
 */
public class BuyXPayYPricingRule implements PricingRule {
    private String sku;
    private int buyQuantity;
    private int payQuantity;

    public BuyXPayYPricingRule(String sku, int buyQuantity, int payQuantity) {
        assert sku != null : "sku cannot be null";
        assert buyQuantity > 0 : "count must be bigger than 0";
        assert payQuantity > 0 : "count must be bigger than 0";
        assert buyQuantity > payQuantity: "count must be bigger than 0";

        this.sku = sku;
        this.buyQuantity = buyQuantity;
        this.payQuantity = payQuantity;
    }

    @Override
    public void apply(ShoppingCart cart) {
        if (cart.isEmpty()) return;

        AtomicInteger counter = new AtomicInteger(0);
        cart.getItems()
                .stream()
                .filter(item -> sku.equals(item.getProduct().getSku()))
                .forEach(item -> {
                    counter.incrementAndGet();
                    if( counter.get() > payQuantity && counter.get()  <= buyQuantity) {
                        item.setPrice(BigDecimal.ZERO);
                     }
                    if(counter.get()==buyQuantity){
                        counter.set(0);
                    }
                });
    }

}
