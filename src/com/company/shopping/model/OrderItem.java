package com.company.shopping.model;

import java.math.BigDecimal;

/**
 * This class holds order line items in the shopping cart
 */
public class OrderItem {
    private Product product;
    private BigDecimal price;
    private Integer quantity;

    public OrderItem(Product product) {
        this(product, null);
    }

    public OrderItem(Product product, BigDecimal price) {
        this.product = product;
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getPrice() {
        return price == null ? product.getPrice() : price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
