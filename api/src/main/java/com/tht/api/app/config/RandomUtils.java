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

        final int supplementDigitSize = digits - (int) (Math.log10(randomNumber) + 1);

        if(supplementDigitSize > 0) {
            return (int) (randomNumber * Math.pow(10, supplementDigitSize))
                + getSupplementNumber(supplementDigitSize);
        }

        return randomNumber;
    }

    private int getSupplementNumber(final int digits) {

        StringBuilder supplementNumber = new StringBuilder();

        for (int i = 0; i < digits; i++) {
            supplementNumber.append(getNumberOfDigits(1));
        }

        return Integer.parseInt(supplementNumber.toString());
    }
}
