package com.tht.api.app.repository.user;

import com.tht.api.app.entity.user.UserToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTokenRepository extends JpaRepository<UserToken, Long> {

    Optional<UserToken> findByAccessToken(final String accessToken);

}
