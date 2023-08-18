package com.tht.api.app.unit.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.anyInt;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.when;

import com.tht.api.app.config.RandomUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomUtilsTest {

    @Test
    @DisplayName("난수 생성 자리수 테스트")
    void randomNumber() {

        int digits = 3;
        int number = RandomUtils.getInstance().getNumberOfDigits(digits);
        assertThat(number).isBetween(0, (int) Math.pow(10, 3));
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

        assertThat(String.valueOf(number)).hasSize(digits);
        assertThat(number).isEqualTo(111111);
    }

}