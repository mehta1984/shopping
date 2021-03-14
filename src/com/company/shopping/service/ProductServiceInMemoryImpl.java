package com.company.shopping.service;

import com.company.shopping.model.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class ProductServiceInMemoryImpl implements ProductService {
    private Map<String, Product> productMap;

    public ProductServiceInMemoryImpl() {
        productMap = new HashMap<>();
        productMap.put("ipd", new Product("ipd", "Super iPad", new BigDecimal("549.99")));
        productMap.put("mbp", new Product("mbp", "MacBook Pro", new BigDecimal("1399.99")));
        productMap.put("atv", new Product("atv", "Apple TV", new BigDecimal("109.50")));
        productMap.put("vga", new Product("vga", "VGA adapter", new BigDecimal("30.00")));
    }

    @Override
    public Product findProductBySku(String sku) {
        return productMap.get(sku);
    }
}
