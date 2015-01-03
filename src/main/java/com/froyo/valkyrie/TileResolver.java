package com.froyo.valkyrie;

import com.froyo.valkyrie.entities.PlanetTile;
import com.froyo.valkyrie.entities.SpaceTile;

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
