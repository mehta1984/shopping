package com.company.shopping.rules;

import com.company.shopping.model.ShoppingCart;

/**
 * General interface to apply Pricing rules
 */
public interface PricingRule {
    void apply(ShoppingCart cart);
}
