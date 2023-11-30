package com.tht.api.app.service;

import com.tht.api.app.entity.user.UserToken;
import com.tht.api.app.repository.user.UserTokenRepository;
import com.tht.api.exception.custom.UserTokenException;
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

}
