package com.tht.infra.user.repository;


import com.tht.infra.user.UserAlarmAgreement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAlarmAgreementRepository extends JpaRepository<UserAlarmAgreement, Long> {

    Optional<UserAlarmAgreement> findByUserUuid(final String userUuid);
}
