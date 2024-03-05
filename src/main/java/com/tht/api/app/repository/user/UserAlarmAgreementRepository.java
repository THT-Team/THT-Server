package com.tht.api.app.repository.user;

import com.tht.api.app.entity.user.UserAlarmAgreement;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAlarmAgreementRepository extends JpaRepository<UserAlarmAgreement, Long> {

    Optional<UserAlarmAgreement> findByUserUuid(final String userUuid);
}
