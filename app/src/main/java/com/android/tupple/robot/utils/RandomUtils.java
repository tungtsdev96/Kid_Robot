package com.android.tupple.robot.utils;

import java.util.Random;

/**
 * Created by tungts on 10/18/2017.
 */

public class RandomUtils {

    //random int from min to max
    public static int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    //random new number # previous number
    // min = 0
    public static int random(int min, int max, int oldNumber){
        if (min == max){
            return min;
        }
        int size = max - min + 1;
        return (oldNumber + randInt(min,max-1)+1)%size;
    }

}
