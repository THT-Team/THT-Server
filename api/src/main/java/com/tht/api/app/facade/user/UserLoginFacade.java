package com.tht.api.app.facade.user;

import com.tht.api.app.config.security.TokenProvider;
import com.tht.api.app.config.security.TokenResponse;
import com.tht.api.app.entity.user.User;
import com.tht.api.app.facade.Facade;
import com.tht.api.app.facade.user.request.UserLoginRequest;
import com.tht.api.app.facade.user.request.UserSNSLoginRequest;
import com.tht.api.app.facade.user.response.UserLoginResponse;
import com.tht.api.app.service.UserDeviceKeyService;
import com.tht.api.app.service.UserService;
import com.tht.api.app.service.UserSnsService;
import lombok.RequiredArgsConstructor;

@Facade
@RequiredArgsConstructor
public class UserLoginFacade {

    private final TokenProvider tokenProvider;
    private final UserService userService;
    private final UserDeviceKeyService deviceKeyService;
    private final UserSnsService userSnsService;

    public UserLoginResponse login(final UserLoginRequest request) {

        final User user = userService.findByPhoneNumber(request.phoneNumber());

        deviceKeyService.create(user.getUserUuid(), request.deviceKey());

        final TokenResponse tokenResponse = tokenProvider.generateJWT(user);

        return tokenResponse.toLoginResponse();
    }

    public UserLoginResponse snsLogin(final UserSNSLoginRequest request) {

        final String userUuid = userSnsService.findUserUuidBySnsIdKey(request.snsType(),
            request.snsUniqueId());

        final User user = userService.findByUserUuidForAuthToken(userUuid);

        return tokenProvider.generateJWT(user).toLoginResponse();
    }
}
