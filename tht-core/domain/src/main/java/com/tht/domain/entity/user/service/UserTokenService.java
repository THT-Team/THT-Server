package com.tht.domain.entity.user.service;

import com.tht.domain.entity.user.UserToken;
import com.tht.domain.entity.user.exception.UserTokenException;
import com.tht.domain.entity.user.repository.UserTokenRepository;
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
