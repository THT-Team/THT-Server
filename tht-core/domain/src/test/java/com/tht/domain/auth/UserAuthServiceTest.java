package com.tht.domain.auth;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserAuthServiceTest {

    @Mock
    UserAuthRepository userAuthRepository;

    @InjectMocks
    UserAuthService userAuthService;

    @Test
    @DisplayName("핸드폰 번호로 유저 조회 (실패)")
    void findUserByPhoneNumber_fail() {

        when(userAuthRepository.findByPhoneNumber(anyString())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userAuthService.findByPhoneNumber(""))
            .isInstanceOf(UnAuthException.class)
            .hasMessageContaining("존재하지 않는 유저 번호입니다.");
    }

    @Test
    @DisplayName("유저 uuid 로 유저 조회 (실패)")
    void findByUUID_fail() {

        when(userAuthRepository.findByUserUuid(anyString())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userAuthService.findByUserUuidForAuthToken(""))
            .isInstanceOf(UnAuthException.class)
            .hasMessageContaining("존재하지 않는 회원번호 입니다.");
    }
}