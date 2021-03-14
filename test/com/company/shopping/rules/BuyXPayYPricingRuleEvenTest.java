package com.company.shopping.rules;

import com.company.shopping.model.Product;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * This is price Rule where Buy 4 items at price of 2
 */
public class BuyXPayYPricingRuleEvenTest extends PricingRuleTest {

    @Override
    protected PricingRule createPriceRule() {
        return new BuyXPayYPricingRule("atv", 4,2);
    }

    @Test
    public void buy4AtPriceOf2Test() {
        Product atv = productService.findProductBySku("atv");

        shoppingCart.addItem(atv);
        shoppingCart.addItem(atv);
        shoppingCart.addItem(atv);
        shoppingCart.addItem(atv);

        assertEquals(new BigDecimal("219.00"), shoppingCart.getTotalPrice());
    }

    @Test
    public void buy8AtPriceOf4Test() {
        Product atv = productService.findProductBySku("atv");

        shoppingCart.addItem(atv);
        shoppingCart.addItem(atv);
        shoppingCart.addItem(atv);
        shoppingCart.addItem(atv);

        shoppingCart.addItem(atv);
        shoppingCart.addItem(atv);
        shoppingCart.addItem(atv);
        shoppingCart.addItem(atv);
        assertEquals(new BigDecimal("438.00"), shoppingCart.getTotalPrice());
    }



    @Test
    public void buy6AtPriceOf4Test() {
        Product atv = productService.findProductBySku("atv");

        shoppingCart.addItem(atv);
        shoppingCart.addItem(atv);
        shoppingCart.addItem(atv);
        shoppingCart.addItem(atv);

        shoppingCart.addItem(atv);
        shoppingCart.addItem(atv);
        assertEquals(new BigDecimal("438.00"), shoppingCart.getTotalPrice());
    }

}
