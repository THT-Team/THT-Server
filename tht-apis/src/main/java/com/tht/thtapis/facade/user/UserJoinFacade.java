package com.tht.thtapis.facade.user;

import com.tht.infra.config.aligo.AligoUtils;
import com.tht.infra.user.User;
import com.tht.infra.user.exception.UserCustomException;
import com.tht.thtapis.facade.Facade;
import com.tht.thtapis.facade.user.request.UserSignUpRequest;
import com.tht.thtapis.facade.user.request.UserSnsSignUpRequest;
import com.tht.thtapis.facade.user.response.AuthNumberResponse;
import com.tht.thtapis.facade.user.response.UserNickNameValidResponse;
import com.tht.thtapis.facade.user.response.UserSignUpInfoResponse;
import com.tht.thtapis.security.TokenDto;
import com.tht.thtapis.security.TokenProvider;
import com.tht.thtapis.service.*;
import com.tht.thtcommonutils.utils.RandomUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Facade
@Transactional
@RequiredArgsConstructor
public class UserJoinFacade {

    private static final int DIGITS_OF_AUTH_NUMBER = 6;

    private final TokenProvider tokenProvider;
    private final UserService userService;
    private final UserAgreementService userAgreementService;
    private final UserLocationInfoService userLocationInfoService;
    private final UserProfilePhotoService userProfilePhotoService;
    private final UserInterestsService userInterestsService;
    private final UserIdealTypeService userIdealTypeService;
    private final UserDeviceKeyService userDeviceKeyService;
    private final UserSnsService userSnsService;
    private final UserAlarmAgreementService userAlarmAgreementService;
    private final UserTokenService userTokenService;

    public AuthNumberResponse issueAuthenticationNumber(final String phoneNumber) {

        final int authNumber = RandomUtils.getInstance()
            .getFullNumberOfDigits(DIGITS_OF_AUTH_NUMBER);
        AligoUtils.sendAuthNumber(phoneNumber, String.valueOf(authNumber));

        return new AuthNumberResponse(phoneNumber, authNumber);
    }

    public UserNickNameValidResponse checkDuplicateNickName(final String nickName) {
        return new UserNickNameValidResponse(userService.isExistUserName(nickName));
    }

    public TokenDto signUp(final UserSignUpRequest request) {
        final User user = userService.createUser(request.toEntity());

        userAgreementService.create(request.makeAgreementToEntity(user.getUserUuid()));
        userAlarmAgreementService.create(user.getUserUuid());
        userLocationInfoService.create(request.makeUserLocationToEntity(user.getUserUuid()));
        userProfilePhotoService.createOf(request.makeUserProfilePhotoList(user.getUserUuid()));
        userInterestsService.createOf(request.makeUserInterestsList(user.getUserUuid()));
        userIdealTypeService.createOf(request.makeUserIdealTypeList(user.getUserUuid()));
        userDeviceKeyService.create(user.getUserUuid(), request.deviceKey());

        if (request.snsType().isSns()) {
            userSnsService.create(user.getUserUuid(), request.snsType(), request.snsUniqueId(),
                request.email());
        }

        return getUserSignUpResponse(user);
    }

    private TokenDto getUserSignUpResponse(User user) {
        final TokenDto tokenDto = tokenProvider.generateJWT(user);
        userTokenService.create(user.getUserUuid(), tokenDto.accessToken());

        return tokenDto;
    }

    public UserSignUpInfoResponse getUserSignUpInfo(final String phoneNumber) {

        return UserSignUpInfoResponse.ofEnum(userSnsService.findAllByPhoneNumber(phoneNumber));
    }

    public TokenDto integratedSnsId(final UserSnsSignUpRequest request) {

        final User user = userService.findByPhoneNumber(request.phoneNumber());

        if (userSnsService.isExistIntegratedUserInfo(user.getUserUuid(), request.snsType(),
            request.snsUniqueId())) {
            throw UserCustomException.duplicateIntegrated(user.getPhoneNumber(), request.snsType());
        }

        userSnsService.create(user.getUserUuid(), request.snsType(), request.snsUniqueId(),
            request.email());
        userDeviceKeyService.create(user.getUserUuid(), request.deviceKey());

        return getUserSignUpResponse(user);
    }
}
