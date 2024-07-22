package com.tht.domain.dailyfalling.service;

import com.tht.domain.entity.dailyfalling.repository.DailyFallingRepository;
import com.tht.domain.entity.dailyfalling.service.DailyFallingService;
import com.tht.domain.exception.EntityStateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DailyFallingServiceTest {

    @Mock
    DailyFallingRepository dailyFallingRepository;

    @InjectMocks
    DailyFallingService dailyFallingService;

    @Test
    @DisplayName("오늘의 주제가 존재하지 않을 때 exception")
    void doNotExist() {

        when(dailyFallingRepository.existsById(anyLong())).thenReturn(false);

        assertThatThrownBy(() -> dailyFallingService.existByIdxAndActiveToday(1))
            .isInstanceOf(EntityStateException.class)
            .hasMessageContaining("해당 겂에 해당하는 DailyFalling 가(이) 존재하지 않습니다.");
    }
}