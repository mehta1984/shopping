package com.company.shopping.model;


import com.company.shopping.model.Product;
import com.company.shopping.rules.PricingRule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * This is shopping cart model. It stores all the order lines items.
 * This class required pricing rules to be initialized prior to apply pricing rule to the cart
 */
public class ShoppingCart {
    private List<OrderItem> items;
    private List<PricingRule> rules;

    public ShoppingCart(List<PricingRule> rules) {
        this.rules = rules;
        this.items = new ArrayList<OrderItem>();
    }

    public void addItem(Product product) {
        items.add(new OrderItem(product));
    }

    public List<OrderItem> getItems() {
        return this.items;
    }

    private void applyPriceRules() {
        if (rules == null || rules.isEmpty()) return;

        rules.stream().forEach(rule -> rule.apply(this));
    }

    public BigDecimal getTotalPrice() {
        if (isEmpty()) {
            return BigDecimal.ZERO;
        }

        this.applyPriceRules();
        return items.stream().map(OrderItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean isEmpty() {
        return items == null || items.isEmpty();
    }
}
