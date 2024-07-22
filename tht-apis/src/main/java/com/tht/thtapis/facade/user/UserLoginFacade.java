package com.tht.thtapis.facade.user;

import com.tht.domain.entity.user.User;
import com.tht.domain.entity.user.UserToken;
import com.tht.thtapis.facade.Facade;
import com.tht.thtapis.facade.user.request.UserLoginRequest;
import com.tht.thtapis.facade.user.request.UserSNSLoginRequest;
import com.tht.thtapis.security.TokenProvider;
import com.tht.thtapis.security.TokenDto;
import com.tht.domain.entity.user.service.UserDeviceKeyService;
import com.tht.domain.entity.user.service.UserService;
import com.tht.domain.entity.user.service.UserSnsService;
import com.tht.domain.entity.user.service.UserTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Facade
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserLoginFacade {

    private final TokenProvider tokenProvider;
    private final UserService userService;
    private final UserDeviceKeyService deviceKeyService;
    private final UserSnsService userSnsService;
    private final UserTokenService userTokenService;

    @Transactional
    public TokenDto login(final UserLoginRequest request) {

        final User user = userService.findByPhoneNumber(request.phoneNumber());
        deviceKeyService.create(user.getUserUuid(), request.deviceKey());

        return getGenerateJWT(user);
    }

    private TokenDto getGenerateJWT(final User user) {

        final TokenDto tokenDto = tokenProvider.generateJWT(user);
        userTokenService.findByUserUuid(user.getUserUuid()).refresh(tokenDto.accessToken());

        return tokenDto;
    }

    @Transactional
    public TokenDto snsLogin(final UserSNSLoginRequest request) {

        final String userUuid = userSnsService.findUserUuidBySnsIdKey(request.snsType(), request.snsUniqueId());
        final User user = userService.findByUserUuidForAuthToken(userUuid);

        return getGenerateJWT(user);
    }

    @Transactional
    public TokenDto refresh(final String requestHeaderAuth) {

        final String accessToken = tokenProvider.getParseJwt(requestHeaderAuth);
        final UserToken userToken = userTokenService.findByAccessToken(accessToken);

        final User user = userService.findByUserUuidForAuthToken(userToken.getUserUuid());

        userToken.checkRefreshExpired();

        TokenDto tokenDto = tokenProvider.generateJWT(user);
        userToken.refresh(tokenDto.accessToken());

        return tokenDto;
    }
}
