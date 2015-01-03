package com.froyo.tradergalaxy.entities;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * EngineeringBay
 */
public class EngineeringBay {

    private Set<Product> products;

    public EngineeringBay() {
        this.products = Sets.newHashSet();
    }
}
