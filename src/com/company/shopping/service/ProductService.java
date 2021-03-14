package com.company.shopping.service;

import com.company.shopping.model.Product;

public interface ProductService {

    Product findProductBySku(String sku);
}
