package com.tht.infra.user.repository;


import com.tht.infra.user.UserAgreement;
import com.tht.infra.user.repository.querydsl.UserAgreementCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAgreementRepository extends JpaRepository<UserAgreement, Long>, UserAgreementCustomRepository {

    Optional<UserAgreement> findByUserUuid(final String userUuid);
}
