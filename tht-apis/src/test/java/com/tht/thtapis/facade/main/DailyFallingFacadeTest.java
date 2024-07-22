package com.tht.thtapis.facade.main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.when;

import java.util.Optional;

import com.tht.thtapis.facade.main.response.DailyFallingResponse;
import com.tht.domain.entity.dailyfalling.service.DailyFallingActiveService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DailyFallingFacadeTest {

    @Mock
    DailyFallingActiveService dailyFallingActiveService;

    @InjectMocks
    DailyFallingFacade dailyFallingFacade;

    @Test
    @DisplayName("그날의 주제어가 등록되지 않았을 때 음수오 빈값 리턴")
    void emptyValue() {

        when(dailyFallingActiveService.findActiveInfo()).thenReturn(Optional.empty());

        DailyFallingResponse dailyFallingList = dailyFallingFacade.getDailyFallingList();

        assertThat(dailyFallingList.expirationUnixTime()).isEqualTo(-1);
        assertThat(dailyFallingList.fallingTopicList()).isEmpty();

    }
}