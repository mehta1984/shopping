package com.company.shopping.rules;

import com.company.shopping.model.ShoppingCart;
import java.math.BigDecimal;

/**
 * If qty for product increased Bulk buy rule quantity, then fixed price apply for all items.
 */
public class BulkBuyPricingRule implements PricingRule {

    private String sku;
    private int quantity;
    private BigDecimal price;

    public BulkBuyPricingRule(String sku, int quantity, BigDecimal price) {
        assert sku != null : "sku cannot be null";
        assert quantity > 0 : "count must be bigger than 0";
        assert price != null && price.doubleValue() > 0 : "invalid price";

        this.sku = sku;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public void apply(ShoppingCart cart) {
        if (!checkCondition(cart))
            return;

        cart.getItems()
                .stream()
                .filter(item -> sku.equals(item.getProduct().getSku()))
                .forEach(item -> item.setPrice(price));
    }

    private boolean checkCondition(ShoppingCart cart) {
        if (cart.isEmpty())
            return false;

        return cart.getItems()
                .stream()
                .filter(item -> sku.equals(item.getProduct().getSku()))
                .count() > quantity;
    }
}
