package com.company.shopping.rules;

import java.util.ArrayList;
import java.util.List;

import com.company.shopping.model.ShoppingCart;
import com.company.shopping.service.ProductService;
import com.company.shopping.service.ProductServiceInMemoryImpl;
import org.junit.Before;


public abstract class PricingRuleTest {
    protected ShoppingCart shoppingCart;
    protected ProductService productService;

    @Before
    public void setupRules() {
        List<PricingRule> rules = new ArrayList<>();
        rules.add(createPriceRule());
        shoppingCart = new ShoppingCart(rules);
        productService = new ProductServiceInMemoryImpl();
    }

    protected abstract PricingRule createPriceRule();
}
