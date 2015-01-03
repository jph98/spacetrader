package com.froyo.valkyrie.entities;

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
