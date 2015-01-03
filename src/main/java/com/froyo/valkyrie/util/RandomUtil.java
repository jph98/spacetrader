package com.froyo.valkyrie.util;

import java.util.Random;

/**
 * RandomUtil
 */
public class RandomUtil {

    public static int numberInRange(int min, int max) {

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static int numberInRange(double min, double max) {
        return numberInRange((int) min, (int) max);
    }
}
