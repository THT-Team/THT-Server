package com.tht.api.app.facade.user;

import com.tht.api.app.config.RandomUtils;
import com.tht.api.app.config.aligo.AligoUtils;
import com.tht.api.app.entity.user.User;
import com.tht.api.app.facade.Facade;
import com.tht.api.app.facade.user.response.UserNickNameValidResponse;
import com.tht.api.app.facade.user.request.UserSignUpRequest;
import com.tht.api.app.facade.user.response.AuthNumberResponse;
import com.tht.api.app.service.UserAgreementService;
import com.tht.api.app.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Facade
@Transactional
@RequiredArgsConstructor
public class UserJoinFacade {
    private static final int DIGITS_OF_AUTH_NUMBER = 6;

    private final UserService userService;
    private final UserAgreementService userAgreementService;

    public AuthNumberResponse issueAuthenticationNumber(final String phoneNumber) {

        final int authNumber = RandomUtils.getInstance().getFullNumberOfDigits(DIGITS_OF_AUTH_NUMBER);
        AligoUtils.sendAuthNumber(phoneNumber, String.valueOf(authNumber));

        return new AuthNumberResponse(phoneNumber, authNumber);
    }

    public UserNickNameValidResponse checkDuplicateNickName(final String nickName) {
        return new UserNickNameValidResponse(userService.isExistUserName(nickName));
    }

    public void signUp(final UserSignUpRequest request) {
        final User user = userService.createUser(request.toEntity());
        userAgreementService.create(request.getAgreementEntity(), user.getUserUuid());

    }
}
