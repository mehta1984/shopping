package com.company.shopping.service;

import com.company.shopping.model.ShoppingCart;
import com.company.shopping.rules.PricingRule;

import java.math.BigDecimal;
import java.util.List;

/**
 * Check out API. It expose scan method and get Total method.
 * It uses shopping cart and product service to find product and add into shopping cart order lineItems
 */
public class Checkout {

    private ShoppingCart shoppingCart;
    private ProductService productService;

    public Checkout(List<PricingRule> rules) {
        this.shoppingCart = new ShoppingCart(rules);
        this.productService = new ProductServiceInMemoryImpl();
    }

    public void scan(String sku) {
        this.shoppingCart.addItem(productService.findProductBySku(sku));
    }

    public BigDecimal total() {
        return shoppingCart.getTotalPrice();
    }
}
