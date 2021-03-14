package com.company.shopping.rules;

import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import com.company.shopping.model.Product;
import org.junit.Test;


public class FreeItemPricingRuleTest extends PricingRuleTest {

    /**
     * we will bundle in a free VGA adapter free of charge with every MacBook Pro sold
     */
    @Override
    protected PricingRule createPriceRule() {
        return new FreeItemPricingRule("mbp", "vga");
    }

    @Test
    public void testFreeBundle() {
        Product mbp = productService.findProductBySku("mbp");
        Product vga = productService.findProductBySku("vga");

        shoppingCart.addItem(mbp);
        shoppingCart.addItem(vga);

        assertEquals(new BigDecimal("1399.99"), shoppingCart.getTotalPrice());
    }

    @Test
    public void testOneMoreTargetBundled() {
        Product mbp = productService.findProductBySku("mbp");
        Product vga = productService.findProductBySku("vga");

        shoppingCart.addItem(mbp);
        shoppingCart.addItem(vga);
        // need to pay one vga
        shoppingCart.addItem(vga);

        assertEquals(new BigDecimal("1429.99"), shoppingCart.getTotalPrice());
    }

    @Test
    public void testOneLessTargetBundled() {
        Product mbp = productService.findProductBySku("mbp");
        Product vga = productService.findProductBySku("vga");

        shoppingCart.addItem(mbp);
        shoppingCart.addItem(vga);

        shoppingCart.addItem(mbp);

        assertEquals(new BigDecimal("2799.98"), shoppingCart.getTotalPrice());
    }

}
