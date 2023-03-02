package com.tht.api.app.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.anyInt;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.when;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;

class RandomUtilsTest {

    @RepeatedTest(100)
    @DisplayName("난수 생성 자리수 테스트")
    void randomNumber() {

        int digits = 3;
        int number = RandomUtils.getInstance().getNumberOfDigits(digits);
        assertThat(number).isBetween((int) Math.pow(10, 2) , (int) Math.pow(10, 3));
    }

    @Test
    @DisplayName("자리수가 꽉찬 난수 생성")
    void fullRandomNumber() {

        int digits = 6;
        RandomUtils randomUtils = mock(RandomUtils.class);
        when(randomUtils.getNumberOfDigits(anyInt())).thenReturn(1);
        when(randomUtils.getFullNumberOfDigits(anyInt())).thenCallRealMethod();

        //when
        int number = randomUtils.getFullNumberOfDigits(digits);

        System.out.println("number = " + number);
        assertThat(String.valueOf(number)).hasSize(digits);
    }

}