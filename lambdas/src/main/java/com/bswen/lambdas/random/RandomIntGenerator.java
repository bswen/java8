package com.bswen.lambdas.random;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created on 2018/5/9.
 */
public class RandomIntGenerator {

    private static int generateByThreadLocalRandom(int upperLimit) {
        //java 1.7 +
        return ThreadLocalRandom.current().nextInt(0, upperLimit + 1);
    }

    //the recommended way.
    private static int generateByThreadLocalRandom2(int rangeFrom,int rangeTo) {
        if(rangeFrom>=rangeTo) throw new IllegalArgumentException("bad param");
        //java 1.7 +
        return ThreadLocalRandom.current().nextInt(rangeFrom, rangeTo + 1);
    }

    //the java.util.Random class is often preferable to java.lang.Math.random().
    private static int generateByRandom(int upperLimit) {
        Random random = new Random();
        return random.nextInt(upperLimit);
    }

    private static int generateByMathRandom(int upperLimit) {
        final double d = Math.random();
        return (int)(d*upperLimit);
    }

    private static int generateByMathRandomRange(int rangeFrom,int rangeTo) {
        if(rangeFrom<0) throw new IllegalArgumentException("rangeFrom must greater than 0");
        return rangeFrom + (int)(Math.random() * ((rangeTo - rangeFrom) + 1));
    }

    private static int generateByTime(int upperLimit) {
        final long l = System.currentTimeMillis();
        return (int)( l % upperLimit );
    }

    public static void main(String[] args) {
        int upperLimit = 100;
        for(int i=0;i<10;i++) {
            //System.out.println(generateByTime(upperLimit));
            //System.out.println(generateByMathRandom(upperLimit));
            //System.out.println(generateByRandom(upperLimit));
            //System.out.println(generateByThreadLocalRandom(upperLimit));
            //System.out.println(generateByMathRandomRange(50,120));
            System.out.println(generateByThreadLocalRandom2(-100,-10));
        }
    }
}
