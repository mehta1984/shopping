package com.company.shopping.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.company.shopping.rules.BulkBuyPricingRule;
import com.company.shopping.rules.FreeItemPricingRule;
import com.company.shopping.rules.BuyXPayYPricingRule;
import com.company.shopping.rules.PricingRule;
import org.junit.Before;
import org.junit.Test;


public class CheckoutTest {
    private List<PricingRule> rules;

    @Before
    public void initialSetup() {
        rules = new ArrayList<>();
        rules.add(new BuyXPayYPricingRule("atv", 3,2));
        rules.add(new BulkBuyPricingRule("ipd", 4, new BigDecimal("499.99")));
        rules.add(new FreeItemPricingRule("mbp", "vga"));
    }

    @Test
    public void testScenarioOne() {
        Checkout co = new Checkout(rules);
        co.scan("atv");
        co.scan("atv");
        co.scan("atv");
        co.scan("vga");
        assertEquals(new BigDecimal("249.00"), co.total());
    }

    @Test
    public void testSenarioTwo() {
        Checkout co = new Checkout(rules);
        co.scan("atv");
        co.scan("ipd");
        co.scan("ipd");
        co.scan("atv");
        co.scan("ipd");
        co.scan("ipd");
        co.scan("ipd");
        assertEquals(new BigDecimal("2718.95"), co.total());
    }

    @Test
    public void testSenarioThree() {
        Checkout co = new Checkout(rules);
        co.scan("mbp");
        co.scan("vga");
        co.scan("ipd");
        assertEquals(new BigDecimal("1949.98"), co.total());
    }
}
