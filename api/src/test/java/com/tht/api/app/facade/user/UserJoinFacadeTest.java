package com.tht.api.app.facade.user;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.when;

import com.tht.api.app.config.security.TokenProvider;
import com.tht.api.app.config.security.TokenResponse;
import com.tht.api.app.entity.user.User;
import com.tht.api.app.facade.user.request.UserSignUpRequest;
import com.tht.api.app.fixture.user.UserSignUpRequestFixture;
import com.tht.api.app.service.UserAgreementService;
import com.tht.api.app.service.UserDeviceKeyService;
import com.tht.api.app.service.UserIdealTypeService;
import com.tht.api.app.service.UserInterestsService;
import com.tht.api.app.service.UserLocationInfoService;
import com.tht.api.app.service.UserProfilePhotoService;
import com.tht.api.app.service.UserService;
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
    TokenProvider tokenProvider;

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
        //위치정보
        verify(userLocationService).create(request.makeUserLocationToEntity(any()));
        //사진
        verify(userProfilePhotoService).createOf(request.makeUserProfilePhotoList(any()));
        //관심사
        verify(userInterestsService).createOf(request.makeUserInterestsList(any()));
        //이상형
        verify(userIdealTypeService).createOf(request.makeUserIdealTypeList(any()));
        //디바이스 키
        verify(userDeviceKeyService).create(request.makeDeviceKeyToEntity(any()));

    }

}