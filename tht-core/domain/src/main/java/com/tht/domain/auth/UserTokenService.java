package com.tht.domain.auth;

import com.tht.infra.user.UserToken;
import com.tht.infra.user.exception.UserTokenException;
import com.tht.infra.user.repository.UserTokenRepository;
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

    public void renewal(final String userUuid, final String accessToken) {

        final Optional<UserToken> userToken = userTokenRepository.findByUserUuid(userUuid);

        if (userToken.isPresent()) {
            userToken.get().refresh(accessToken);
            return;
        }
        create(userUuid, accessToken);
    }

    public void create(final String userUuid, final String accessToken) {
        userTokenRepository.save(UserToken.create(userUuid, accessToken));
    }
}
