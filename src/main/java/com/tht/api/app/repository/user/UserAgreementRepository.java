package com.tht.api.app.repository.user;

import com.tht.api.app.entity.user.UserAgreement;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAgreementRepository extends JpaRepository<UserAgreement, Long> {

    Optional<UserAgreement> findByUserUuid(final String userUuid);
}
