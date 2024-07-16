package com.tht.domain.auth;

import com.tht.infra.user.User;
import com.tht.infra.user.repository.UserRepository;

import java.util.Optional;

public interface UserAuthRepository extends UserRepository {

    Optional<User> findByPhoneNumber(final String phoneNumber);

    Optional<User> findByUserUuid(final String userUuid);

}
