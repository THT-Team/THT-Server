package com.tht.api.app.service;

import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.when;
import static org.mockito.Mockito.mock;

import com.tht.api.app.entity.user.User;
import com.tht.api.app.repository.UserRepository;
import com.tht.api.exception.custom.EntityStateException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    @DisplayName("User Info 엔티티 데이터 생성 전 휴대전환 중복 체크")
    void duplicateCheckPhoneNumber_at_UserCreate() {

        when(userRepository.existsByPhoneNumber(anyString())).thenReturn(true);

        User user = mock(User.class);
        when(user.getPhoneNumber()).thenReturn("중복번호");

        Assertions.assertThatThrownBy(() -> userService.createUser(user))
            .isInstanceOf(EntityStateException.class)
            .hasMessageContaining("phoneNumber 값이 이미 존재합니다.");
    }

    @Test
    @DisplayName("User Info 엔티티 데이터 생성 전 닉네임 중복 체크")
    void duplicateCheckUserName_at_UserCreate() {

        when(userRepository.existsByPhoneNumber(anyString())).thenReturn(false);
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        User user = mock(User.class);
        when(user.getPhoneNumber()).thenReturn("중복번호");
        when(user.getUsername()).thenReturn("중복이름");

        Assertions.assertThatThrownBy(() -> userService.createUser(user))
            .isInstanceOf(EntityStateException.class)
            .hasMessageContaining("username 값이 이미 존재합니다.");
    }
}