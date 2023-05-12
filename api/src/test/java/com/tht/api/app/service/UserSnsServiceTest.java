package com.tht.api.app.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.when;

import com.tht.api.app.entity.enums.SNSType;
import com.tht.api.app.repository.UserSnsRepository;
import com.tht.api.exception.custom.EntityStateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserSnsServiceTest {

    @Mock
    UserSnsRepository userSnsRepository;

    @InjectMocks
    UserSnsService userSnsService;

    @Test
    @DisplayName("SNS 고유 ID 로 가입된 이력이 있는지 판별")
    void duplicateSnsId() {
        when(userSnsRepository.existsBySnsTypeAndSnsUniqueId(any(), anyString())).thenReturn(true);

        SNSType normal = SNSType.NORMAL;
        String snsUniqueId = "snsUniqueId";

        assertThatThrownBy(
            () -> userSnsService.create("user-uuid", normal, snsUniqueId))
            .isInstanceOf(EntityStateException.class)
            .hasMessageContaining("해당 " + normal + " 계정이 이미 존재합니다.");
    }
}