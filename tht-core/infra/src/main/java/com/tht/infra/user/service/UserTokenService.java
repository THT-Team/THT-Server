package com.tht.infra.user.service;

import com.tht.infra.user.UserToken;
import com.tht.infra.user.exception.UserTokenException;
import com.tht.infra.user.repository.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserTokenService {

    private final UserTokenRepository userTokenRepository;

    public UserToken findByAccessToken(final String accessToken) {
        return userTokenRepository.findByAccessToken(accessToken).orElseThrow(
            UserTokenException::notFoundOfAccessToken
        );
    }

    public UserToken findByUserUuid(final String userUuid) {
        return userTokenRepository.findByUserUuid(userUuid).orElseThrow(
            UserTokenException::notFoundOfUserUuid
        );
    }


    public void create(final String userUuid, final String accessToken) {
        userTokenRepository.save(UserToken.create(userUuid, accessToken));
    }
}
