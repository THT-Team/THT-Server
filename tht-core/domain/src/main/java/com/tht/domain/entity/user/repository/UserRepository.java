package com.tht.domain.entity.user.repository;

import com.tht.domain.entity.user.User;
import com.tht.domain.entity.user.repository.querydsl.UserCustomRepository;
import com.tht.enums.EntityState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {

    boolean existsByUsernameAndStateEquals(final String userName, final EntityState state);
    boolean existsByPhoneNumberAndStateEquals(final String phoneNumber, final EntityState state);

    Optional<User> findByPhoneNumber(final String phoneNumber);
    Optional<User> findByUserUuid(final String userUuid);

}
