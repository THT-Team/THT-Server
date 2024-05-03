package com.tht.api.app.repository.user;

import com.tht.api.app.entity.user.UserAgreement;
import com.tht.api.app.repository.user.querydsl.UserAgreementCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAgreementRepository extends JpaRepository<UserAgreement, Long>, UserAgreementCustomRepository {

    Optional<UserAgreement> findByUserUuid(final String userUuid);
}
