package com.tht.thtapis.facade.user;

import com.tht.infra.user.User;
import com.tht.infra.user.UserToken;
import com.tht.thtapis.facade.Facade;
import com.tht.thtapis.facade.user.request.UserLoginRequest;
import com.tht.thtapis.facade.user.request.UserSNSLoginRequest;
import com.tht.thtapis.facade.user.response.UserLoginResponse;
import com.tht.thtapis.security.TokenProvider;
import com.tht.thtapis.security.TokenResponse;
import com.tht.thtapis.service.UserDeviceKeyService;
import com.tht.thtapis.service.UserService;
import com.tht.thtapis.service.UserSnsService;
import com.tht.thtapis.service.UserTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Facade
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserLoginFacade {

    private final TokenProvider tokenProvider;
    private final UserService userService;
    private final UserDeviceKeyService deviceKeyService;
    private final UserSnsService userSnsService;
    private final UserTokenService userTokenService;

    @Transactional
    public UserLoginResponse login(final UserLoginRequest request) {

        final User user = userService.findByPhoneNumber(request.phoneNumber());
        deviceKeyService.create(user.getUserUuid(), request.deviceKey());

        return getGenerateJWT(user).toLoginResponse();
    }

    private TokenResponse getGenerateJWT(final User user) {

        final TokenResponse tokenResponse = tokenProvider.generateJWT(user);
        userTokenService.findByUserUuid(user.getUserUuid()).refresh(tokenResponse.accessToken());

        return tokenResponse;
    }

    @Transactional
    public UserLoginResponse snsLogin(final UserSNSLoginRequest request) {

        final String userUuid = userSnsService.findUserUuidBySnsIdKey(request.snsType(),
            request.snsUniqueId());
        final User user = userService.findByUserUuidForAuthToken(userUuid);

        return getGenerateJWT(user).toLoginResponse();
    }

    @Transactional
    public UserLoginResponse refresh(final String requestHeaderAuth) {

        final String accessToken = tokenProvider.getParseJwt(requestHeaderAuth);
        final UserToken userToken = userTokenService.findByAccessToken(accessToken);

        final User user = userService.findByUserUuidForAuthToken(userToken.getUserUuid());

        userToken.checkRefreshExpired();

        TokenResponse tokenResponse = tokenProvider.generateJWT(user);

        userToken.refresh(tokenResponse.accessToken());

        return tokenResponse.toLoginResponse();
    }
}
