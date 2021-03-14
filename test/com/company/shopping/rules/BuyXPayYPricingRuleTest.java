package com.company.shopping.rules;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import com.company.shopping.model.Product;
import org.junit.Test;

/**
 * This is price Rule where Buy 3 items at price of 2
 */
public class BuyXPayYPricingRuleTest extends PricingRuleTest {

    @Override
    protected PricingRule createPriceRule() {
        return new BuyXPayYPricingRule("atv", 3,2);
    }

    @Test
    public void buy3AtPriceOf2Test() {
        Product atv = productService.findProductBySku("atv");

        shoppingCart.addItem(atv);
        shoppingCart.addItem(atv);
        shoppingCart.addItem(atv);

        assertEquals(new BigDecimal("219.00"), shoppingCart.getTotalPrice());
    }

    @Test
    public void buy9AtPriceOf6Test() {
        Product atv = productService.findProductBySku("atv");

        shoppingCart.addItem(atv);
        shoppingCart.addItem(atv);
        shoppingCart.addItem(atv);

        shoppingCart.addItem(atv);
        shoppingCart.addItem(atv);
        shoppingCart.addItem(atv);

        shoppingCart.addItem(atv);
        shoppingCart.addItem(atv);
        shoppingCart.addItem(atv);

        assertEquals(new BigDecimal("657.00"), shoppingCart.getTotalPrice());
    }

    @Test
    public void buy2AtOriginalPrice() {
        Product atv = productService.findProductBySku("atv");

        shoppingCart.addItem(atv);
        shoppingCart.addItem(atv);

        assertEquals(new BigDecimal("219.00"), shoppingCart.getTotalPrice());
    }

    /**
     * Mix scenario where discount applied for 3 items at price of 2 and two more item at full price
     */
    @Test
    public void buy5ItemAfterGetting3ItemAtPriceOf2Test() {
        Product atv = productService.findProductBySku("atv");

        shoppingCart.addItem(atv);
        shoppingCart.addItem(atv);
        shoppingCart.addItem(atv);
        shoppingCart.addItem(atv);
        shoppingCart.addItem(atv);

        assertEquals(new BigDecimal("438.00"), shoppingCart.getTotalPrice());
    }
}
