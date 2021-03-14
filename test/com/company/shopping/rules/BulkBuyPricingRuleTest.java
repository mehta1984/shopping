package com.company.shopping.rules;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import com.company.shopping.model.Product;
import org.junit.Test;


public class BulkBuyPricingRuleTest extends PricingRuleTest {

    @Override
    protected PricingRule createPriceRule() {
        return new BulkBuyPricingRule("ipd", 4, new BigDecimal("499.99"));
    }

    /**
     * Apply bulk discount when quantity is greater than 4
     */
    @Test
    public void applyBulkDiscountTest() {
        Product ipd = productService.findProductBySku("ipd");

        shoppingCart.addItem(ipd);
        shoppingCart.addItem(ipd);
        shoppingCart.addItem(ipd);
        shoppingCart.addItem(ipd);
        shoppingCart.addItem(ipd);
        shoppingCart.addItem(ipd);

        assertEquals(new BigDecimal("2999.94"), shoppingCart.getTotalPrice());
    }

    /**
     * It should not give discount if qty is less than bulk threshold
     */
    @Test
    public void noDiscountWhenQtyLessThanBulkThreshold() {
        Product ipd = productService.findProductBySku("ipd");

        shoppingCart.addItem(ipd);
        shoppingCart.addItem(ipd);
        shoppingCart.addItem(ipd);
        shoppingCart.addItem(ipd);

        assertEquals(new BigDecimal("2199.96"), shoppingCart.getTotalPrice());
    }

    /**
     * After qty in cart increased than bulk threshold, it will give same discount to all other items
     */
    @Test
    public void fixDiscountForItemsGreaterThanBulkThreshold() {
        Product ipd = productService.findProductBySku("ipd");

        shoppingCart.addItem(ipd);
        shoppingCart.addItem(ipd);
        shoppingCart.addItem(ipd);
        shoppingCart.addItem(ipd);
        shoppingCart.addItem(ipd);
        shoppingCart.addItem(ipd);
        shoppingCart.addItem(ipd);

        assertEquals(new BigDecimal("3499.93"), shoppingCart.getTotalPrice());
    }
}
