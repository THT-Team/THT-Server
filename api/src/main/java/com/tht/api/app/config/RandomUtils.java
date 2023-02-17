package com.tht.api.app.config;

import java.util.Random;

public class RandomUtils {

    private static final Random random = new Random();

    private RandomUtils(){}

    private static class SettingHolder{
        private static final RandomUtils UTILS = new RandomUtils();
    }

    public static RandomUtils getInstance() {
        return SettingHolder.UTILS;
    }


    public int getNumberOfDigits(final int digits){
        return random.nextInt((int) Math.pow(10,digits));
    }

    public int getFullNumberOfDigits(final int digits){
        final int randomNumber = getNumberOfDigits(digits);

        final int scarceDigitSize = digits - (int) (Math.log10(randomNumber) + 1);

        return (int) (randomNumber * Math.pow(10, scarceDigitSize))
            + supplementNumber(scarceDigitSize);
    }

    private int supplementNumber(final int digits) {
        int result = 0;

        for (int i = 1; i <= digits; i++) {
            result += i * Math.pow(10, (double) i - 1);
        }

        return result;
    }
}
