package com.froyo.valkyrie.entities;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Market
 */
public class Market {

    private String name;
    private Set<Product> products;

    public Market() {
        this.products = Sets.newHashSet();
    }

    public void addProduct(Product p, int quantity, double price) {

        p.setQuantity(quantity);
        p.setPrice(price);
        this.products.add(p);
    }
}
