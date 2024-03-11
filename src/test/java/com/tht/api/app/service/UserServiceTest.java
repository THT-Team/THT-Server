package com.tht.api.app.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.when;
import static org.mockito.Mockito.mock;

import com.tht.api.app.entity.user.User;
import com.tht.api.app.fixture.user.UserFixture;
import com.tht.api.app.repository.user.UserRepository;
import com.tht.api.app.service.UserService;
import com.tht.api.exception.custom.EntityStateException;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private static final User USER = UserFixture.make();

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

        assertThatThrownBy(() -> userService.createUser(user))
            .isInstanceOf(EntityStateException.class)
            .hasMessageContaining("phoneNumber 값이 이미 존재합니다.");
    }

    @Test
    @DisplayName("User Info 엔티티 데이터 생성 전 닉네임 중복 체크")
    void duplicateCheckUserName_at_UserCreate() {

        when(userRepository.existsByPhoneNumber(anyString())).thenReturn(false);
        when(userRepository.existsByUsernameAndStateEquals(anyString(), any())).thenReturn(true);

        assertThatThrownBy(() -> userService.createUser(USER))
            .isInstanceOf(EntityStateException.class)
            .hasMessageContaining("username 값이 이미 존재합니다.");
    }

    @Test
    @DisplayName("User 저장 테스트")
    void createNewUser() {


        when(userRepository.existsByPhoneNumber(anyString())).thenReturn(false);
        when(userRepository.existsByUsernameAndStateEquals(anyString(), any())).thenReturn(false);
        when(userRepository.save(USER)).thenReturn(USER);

        assertThat(userService.createUser(USER)).isEqualTo(USER);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    @DisplayName("유저 닉네임 중복 체크 테스트")
    void isExistUserNameServiceTest(final Boolean result) {
        when(userRepository.existsByUsernameAndStateEquals(anyString(), any())).thenReturn(result);

        assertThat(userService.isExistUserName("")).isEqualTo(result);
    }

    @Test
    @DisplayName("핸드폰 번호로 유저 조회 (성공)")
    void findUserByPhoneNumber_success() {

        when(userRepository.findByPhoneNumber(anyString())).thenReturn(Optional.of(USER));

        assertThat(userService.findByPhoneNumber("")).isEqualTo(USER);
    }

    @Test
    @DisplayName("핸드폰 번호로 유저 조회 (실패)")
    void findUserByPhoneNumber_fail() {

        when(userRepository.findByPhoneNumber(anyString())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.findByPhoneNumber(""))
            .isInstanceOf(BadCredentialsException.class)
            .hasMessageContaining("존재하지 않는 유저 번호입니다.");
    }

    @Test
    @DisplayName("유저 uuid 로 유저 조회 (성공)")
    void findByUUID_success() {

        when(userRepository.findByUserUuid(anyString())).thenReturn(Optional.of(USER));

        assertThat(userService.findByUserUuidForAuthToken("")).isEqualTo(USER);
    }

    @Test
    @DisplayName("유저 uuid 로 유저 조회 (실패)")
    void findByUUID_fail() {

        when(userRepository.findByUserUuid(anyString())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.findByUserUuidForAuthToken(""))
            .isInstanceOf(BadCredentialsException.class)
            .hasMessageContaining("존재하지 않는 회원번호 입니다.");
    }

}