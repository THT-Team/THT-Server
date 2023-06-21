package com.tht.api.app.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.tht.api.app.repository.meta.DailyFallingRepository;
import com.tht.api.exception.custom.EntityStateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DailyFallingServiceTest {

    @Mock
    DailyFallingRepository dailyFallingRepository;

    @InjectMocks
    DailyFallingService dailyFallingService;

    @Test
    @DisplayName("오늘의 주제가 존재하지 않을 때 exception")
    void doNotExist() {

        when(dailyFallingRepository.existsByIdxAndActiveDay(anyLong(), any())).thenReturn(false);

        assertThatThrownBy(() -> dailyFallingService.existByIdxAndActiveToday(1))
            .isInstanceOf(EntityStateException.class)
            .hasMessageContaining("해당 겂에 해당하는 DailyFalling 가(이) 존재하지 않거나 지난 폴링 주제어 입니다.");
    }
}