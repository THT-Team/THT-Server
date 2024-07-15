package com.tht.thtapis.facade.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

import com.tht.infra.user.User;
import com.tht.infra.user.exception.UserCustomException;
import com.tht.thtapis.facade.user.request.UserSignUpRequest;
import com.tht.thtapis.facade.user.request.UserSnsSignUpRequest;
import com.tht.thtapis.fixture.user.UserSignUpRequestFixture;
import com.tht.thtapis.fixture.user.UserSnsSignUpRequestFixture;
import com.tht.thtapis.security.TokenProvider;
import com.tht.thtapis.security.TokenResponse;
import com.tht.thtapis.service.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserJoinFacadeTest {

    @Mock
    UserService userService;
    @Mock
    UserAgreementService userAgreementService;
    @Mock
    UserLocationInfoService userLocationService;
    @Mock
    UserProfilePhotoService userProfilePhotoService;
    @Mock
    UserInterestsService userInterestsService;
    @Mock
    UserIdealTypeService userIdealTypeService;
    @Mock
    UserDeviceKeyService userDeviceKeyService;
    @Mock
    UserSnsService userSnsService;
    @Mock
    TokenProvider tokenProvider;
    @Mock
    UserAlarmAgreementService userAlarmAgreementService;
    @Mock
    UserTokenService userTokenService;

    @InjectMocks
    UserJoinFacade userJoinFacade;

    @Test
    @DisplayName("유저 회원가입시 흐름 테스트")
    void userJoinFacadeTest() {

        //give
        UserSignUpRequest request = UserSignUpRequestFixture.make();
        User user = mock(User.class);
        when(user.getUserUuid()).thenReturn("user-uuid-test");
        when(userService.createUser(any())).thenReturn(user);
        when(tokenProvider.generateJWT(any())).thenReturn(new TokenResponse("", 1L));

        //when
        userJoinFacade.signUp(request);

        //then
        verify(userService).createUser(any());

        //약관동의
        verify(userAgreementService).create(request.makeAgreementToEntity(any()));
        //알림동의
        verify(userAlarmAgreementService).create(anyString());
        //위치정보
        verify(userLocationService).create(request.makeUserLocationToEntity(any()));
        //사진
        verify(userProfilePhotoService).createOf(request.makeUserProfilePhotoList(any()));
        //관심사
        verify(userInterestsService).createOf(request.makeUserInterestsList(any()));
        //이상형
        verify(userIdealTypeService).createOf(request.makeUserIdealTypeList(any()));
        //디바이스 키
        verify(userDeviceKeyService).create(anyString(), anyString());

        verify(userSnsService, times(0)).create(anyString(), any(), anyString(), anyString());

    }

    @Test
    @DisplayName("SNS 유저 회원가입시 흐름 테스트")
    void userJoinFacadeSNSTest() {

        //give
        UserSignUpRequest request = UserSignUpRequestFixture.ofSNSType("KAKAO");
        User user = mock(User.class);
        when(user.getUserUuid()).thenReturn("user-uuid-test");
        when(userService.createUser(any())).thenReturn(user);
        when(tokenProvider.generateJWT(any())).thenReturn(new TokenResponse("", 1L));

        //when
        userJoinFacade.signUp(request);

        //then
        verify(userSnsService).create(anyString(), any(), anyString(), anyString());

    }

    @Test
    @DisplayName("유저 SNS 회원가입 통합")
    void integratedUserId() {

        User mock = mock(User.class);
        when(userService.findByPhoneNumber(anyString())).thenReturn(mock);
        when(mock.getUserUuid()).thenReturn("");

        when(userSnsService.isExistIntegratedUserInfo(anyString(), any(), anyString())).thenReturn(
            false);
        doNothing().when(userSnsService).create(anyString(), any(), anyString(), anyString());
        when(tokenProvider.generateJWT(any())).thenReturn(new TokenResponse("", 1L));

        //when
        userJoinFacade.integratedSnsId(UserSnsSignUpRequestFixture.makeSNSType());

        //then
        verify(userSnsService).isExistIntegratedUserInfo(anyString(), any(), anyString());

        //save
        verify(userSnsService).create(anyString(), any(), anyString(), anyString());
    }

    @Test
    @DisplayName("유저 SNS 회원가입 통합 시 중복회원 예외 처리")
    void integratedUserId_duplicateCase() {

        User mock = mock(User.class);
        when(userService.findByPhoneNumber(anyString())).thenReturn(mock);
        when(mock.getUserUuid()).thenReturn("1234");
        when(mock.getPhoneNumber()).thenReturn("1234");

        when(userSnsService.isExistIntegratedUserInfo(anyString(), any(), anyString())).thenReturn(
            true);

        //when
        UserSnsSignUpRequest make = UserSnsSignUpRequestFixture.makeSNSType();

        assertThatThrownBy(
            () -> userJoinFacade.integratedSnsId(make))
            .isInstanceOf(UserCustomException.class)
            .hasMessageContaining("1234 : 해당번호로 가입된 " + make.snsType().name()
                + " 이 존재하거나, 해당 SNS 고유 아이디로 가입한 이력이 존재합니다.");

    }
}