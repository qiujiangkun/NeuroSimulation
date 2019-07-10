package com.jeekrs.neuro_simulation.utils;

import java.util.Random;

public class RandomUtil {
    public static final int RAND_SEED = (int) System.currentTimeMillis();
	public static Random rand = new Random (RAND_SEED);

    public static float nextFloat() {
        return rand.nextFloat();
    }

    public static float nextFloat(float a, float b) {
		if (a > b) {
            float tmp = a;
			a = b;
			b = tmp;
		}
        return rand.nextFloat() * (b - a) + a;
	}
}