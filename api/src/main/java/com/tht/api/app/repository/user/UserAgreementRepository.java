package com.tht.api.app.repository.user;

import com.tht.api.app.entity.user.UserAgreement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAgreementRepository extends JpaRepository<UserAgreement, Long> {

}
