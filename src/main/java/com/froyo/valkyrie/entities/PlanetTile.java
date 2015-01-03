package com.froyo.valkyrie.entities;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 * A planet consists of:
 * - Set of missions
 * - Market for trading products at specific prices
 */
public class PlanetTile {

    public static final int TILE = 0;
    public static final String DESCRIPTION = "PlanetTile";

    private String name;
    private int x;
    private int y;

    private Set<Mission> missions = Sets.newHashSet();
    private Market market;
    private EngineeringBay bay;

    public PlanetTile() {

        this.market = new Market();
    }

    public PlanetTile(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public PlanetTile setName(String name) {
        this.name = name;
        return this;
    }

    public PlanetTile setY(int y) {
        this.y = y;
        return this;
    }

    public PlanetTile setX(int x) {
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
        PlanetTile other = (PlanetTile) obj;
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

    public void createMarket() {
    }

    public void createEngineeringBay() {

    }

    public void createMissions() {

    }
}
