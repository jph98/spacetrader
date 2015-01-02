package com.froyo.tradergalaxy;

import com.google.common.base.MoreObjects;

public class Planet {

    private String name;
    private int x;
    private int y;

    public Planet() {

    }

    public Planet(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public Planet setName(String name) {
        this.name = name;
        return this;
    }

    public Planet setY(int y) {
        this.y = y;
        return this;
    }

    public Planet setX(int x) {
        this.x = x;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Planet other = (Planet) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public String toString() {

        MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(this);
        helper.add("name", name);
        helper.add("x", x);
        helper.add("y", y);

        return helper.toString();
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
