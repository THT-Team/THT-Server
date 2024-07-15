package com.tht.thtapis.facade.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import com.tht.infra.user.User;
import com.tht.infra.user.UserToken;
import com.tht.thtapis.facade.user.response.UserLoginResponse;
import com.tht.thtapis.fixture.user.UserLoginRequestFixture;
import com.tht.thtapis.fixture.user.UserSNSLoginRequestFixture;
import com.tht.thtapis.security.TokenProvider;
import com.tht.thtapis.security.TokenResponse;
import com.tht.thtapis.service.UserDeviceKeyService;
import com.tht.thtapis.service.UserService;
import com.tht.thtapis.service.UserSnsService;
import com.tht.thtapis.fixture.user.UserTokenFixture;
import com.tht.thtapis.service.UserTokenService;
import fixture.user.UserFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserLoginFacadeTest {

    private static final User USER = UserFixture.make();
    private static final UserToken USER_TOKEN = UserTokenFixture.make();

    @Mock
    TokenProvider tokenProvider;
    @Mock
    UserService userService;
    @Mock
    UserDeviceKeyService deviceKeyService;
    @Mock
    UserSnsService userSnsService;
    @Mock
    UserTokenService userTokenService;

    @InjectMocks
    UserLoginFacade userLoginFacade;

    @Test
    @DisplayName("일반 유저 로그인 인수테스트")
    void login() {

        TokenResponse tokenResponse = TokenResponse.of("access token", 1L);

        when(userService.findByPhoneNumber(anyString())).thenReturn(USER);
        doNothing().when(deviceKeyService).create(anyString(), anyString());
        when(tokenProvider.generateJWT(any())).thenReturn(tokenResponse);
        when(userTokenService.findByUserUuid(anyString())).thenReturn(USER_TOKEN);

        //when
        UserLoginResponse result = userLoginFacade.login(UserLoginRequestFixture.make());

        //then
        verify(userService).findByPhoneNumber(anyString());
        verify(deviceKeyService).create(anyString(), anyString());
        verify(tokenProvider).generateJWT(any());

        assertThat(result).isEqualTo(tokenResponse.toLoginResponse());
    }

    @Test
    @DisplayName("SNS 유저 로그인 인수테스트")
    void snsLogin() {

        TokenResponse tokenResponse = TokenResponse.of("access token", 1L);

        when(userSnsService.findUserUuidBySnsIdKey(any(), anyString())).thenReturn("user-uuid");
        when(userService.findByUserUuidForAuthToken(anyString())).thenReturn(USER);
        when(tokenProvider.generateJWT(any())).thenReturn(tokenResponse);
        when(userTokenService.findByUserUuid(anyString())).thenReturn(USER_TOKEN);

        //when
        UserLoginResponse result = userLoginFacade.snsLogin(UserSNSLoginRequestFixture.make());

        //then
        verify(userSnsService).findUserUuidBySnsIdKey(any(), anyString());
        verify(userService).findByUserUuidForAuthToken(anyString());
        verify(tokenProvider).generateJWT(any());

        assertThat(result).isEqualTo(tokenResponse.toLoginResponse());
    }
}