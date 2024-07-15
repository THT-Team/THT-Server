package com.tht.infra.user.repository;

import com.tht.infra.user.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTokenRepository extends JpaRepository<UserToken, Long> {

    Optional<UserToken> findByAccessToken(final String accessToken);

    Optional<UserToken> findByUserUuid(final String userUuid);
}
