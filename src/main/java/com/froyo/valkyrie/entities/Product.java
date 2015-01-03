package com.froyo.valkyrie.entities;

import com.google.common.base.MoreObjects;

/**
 * Product
 */
public class Product {

    private String name;
    private String type;
    private String unit;
    private double quantity;
    private double lowprice;
    private double highprice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getLowprice() {
        return lowprice;
    }

    public void setLowprice(double lowprice) {
        this.lowprice = lowprice;
    }

    public double getHighprice() {
        return highprice;
    }

    public void setHighprice(double highprice) {
        this.highprice = highprice;
    }

    @Override
    public String toString() {

        MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(this);
        helper.add("name", name);
        helper.add("type", type);
        helper.add("unit", unit);
        helper.add("quantity", quantity);
        helper.add("lowprice", lowprice);
        helper.add("highprice", highprice);

        return helper.toString();
    }
}
