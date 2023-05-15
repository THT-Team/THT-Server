package com.tht.api.app.facade.user;

import com.tht.api.app.config.aligo.AligoUtils;
import com.tht.api.app.config.security.TokenProvider;
import com.tht.api.app.config.utils.RandomUtils;
import com.tht.api.app.entity.user.User;
import com.tht.api.app.facade.Facade;
import com.tht.api.app.facade.user.request.UserSignUpInfoResponse;
import com.tht.api.app.facade.user.request.UserSignUpRequest;
import com.tht.api.app.facade.user.response.AuthNumberResponse;
import com.tht.api.app.facade.user.response.UserNickNameValidResponse;
import com.tht.api.app.facade.user.response.UserSignUpResponse;
import com.tht.api.app.service.UserAgreementService;
import com.tht.api.app.service.UserDeviceKeyService;
import com.tht.api.app.service.UserIdealTypeService;
import com.tht.api.app.service.UserInterestsService;
import com.tht.api.app.service.UserLocationInfoService;
import com.tht.api.app.service.UserProfilePhotoService;
import com.tht.api.app.service.UserService;
import com.tht.api.app.service.UserSnsService;
import jakarta.transaction.Transactional;
import java.util.List;
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

    public AuthNumberResponse issueAuthenticationNumber(final String phoneNumber) {

        final int authNumber = RandomUtils.getInstance()
            .getFullNumberOfDigits(DIGITS_OF_AUTH_NUMBER);
        AligoUtils.sendAuthNumber(phoneNumber, String.valueOf(authNumber));

        return new AuthNumberResponse(phoneNumber, authNumber);
    }

    public UserNickNameValidResponse checkDuplicateNickName(final String nickName) {
        return new UserNickNameValidResponse(userService.isExistUserName(nickName));
    }

    public UserSignUpResponse signUp(final UserSignUpRequest request) {
        final User user = userService.createUser(request.toEntity());
        userAgreementService.create(request.makeAgreementToEntity(user.getUserUuid()));
        userLocationInfoService.create(request.makeUserLocationToEntity(user.getUserUuid()));
        userProfilePhotoService.createOf(request.makeUserProfilePhotoList(user.getUserUuid()));
        userInterestsService.createOf(request.makeUserInterestsList(user.getUserUuid()));
        userIdealTypeService.createOf(request.makeUserIdealTypeList(user.getUserUuid()));
        userDeviceKeyService.create(request.makeDeviceKeyToEntity(user.getUserUuid()));

        if (request.snsType().isSns()) {
            userSnsService.create(user.getUserUuid(), request.snsType(), request.snsUniqueId());
        }

        return tokenProvider.generateJWT(user).toSignUpResponse();
    }

    public UserSignUpInfoResponse getUserSignUpInfo(final String phoneNumber) {

        final List<String> userSignUpList = userSnsService.findAllByPhoneNumber(phoneNumber);

        return UserSignUpInfoResponse.of(userSignUpList);
    }
}
