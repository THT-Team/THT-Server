package com.tht.api.app.facade.user;

import com.tht.api.app.config.RandomUtils;
import com.tht.api.app.config.aligo.AligoUtils;
import com.tht.api.app.entity.user.User;
import com.tht.api.app.facade.Facade;
import com.tht.api.app.facade.user.response.UserNickNameValidResponse;
import com.tht.api.app.facade.user.response.UserResponse;
import com.tht.api.app.facade.user.request.UserSignUpRequest;
import com.tht.api.app.facade.user.response.AuthNumberResponse;
import com.tht.api.app.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Facade
@Transactional
@RequiredArgsConstructor
public class UserFacade {
    private static final int DIGITS_OF_AUTH_NUMBER = 6;

    private final UserService userService;

    public UserResponse createUser(UserSignUpRequest request) {
        User user = userService.createUser(request.toEntity());
        return UserResponse.fromEntity(user);

    }

    public AuthNumberResponse issueAuthenticationNumber(final String phoneNumber) {

        final int authNumber = RandomUtils.getInstance().getFullNumberOfDigits(DIGITS_OF_AUTH_NUMBER);
        AligoUtils.sendAuthNumber(phoneNumber, String.valueOf(authNumber));

        return new AuthNumberResponse(phoneNumber, authNumber);
    }

    public UserNickNameValidResponse checkDuplicateNickName(final String nickName) {
        return new UserNickNameValidResponse(userService.isExistUserName(nickName));
    }
}
