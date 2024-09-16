package com.tht.domain.entity.user.service;

import com.tht.domain.entity.user.UserToken;
import com.tht.domain.entity.user.exception.UserTokenException;
import com.tht.domain.entity.user.repository.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    public void refresh(final String userUuid, final String accessToken) {
        generateUserToken(userUuid, accessToken);
    }

    public void generateUserToken(final String userUuid, final String accessToken) {
        final Optional<UserToken> userToken = userTokenRepository.findByUserUuid(userUuid);

        if(userToken.isPresent()) {
            userToken.get().refresh(accessToken);
            return;
        }
        create(userUuid, accessToken);
    }

    private void create(final String userUuid, final String accessToken) {
        userTokenRepository.save(UserToken.create(userUuid, accessToken));
    }
}
