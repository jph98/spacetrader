package com.froyo.tradergalaxy;

import com.froyo.tradergalaxy.entities.PlanetTile;
import com.froyo.tradergalaxy.entities.SpaceTile;

/**
 * TileResolver
 */
public class TileResolver {

    public static String resolve(int tile) {
        if (tile == PlanetTile.TILE) {
            return PlanetTile.DESCRIPTION;
        } else if (tile == SpaceTile.TILE) {
            return SpaceTile.DESCRIPTION;
        }
        return "Unknown";
    }
}
