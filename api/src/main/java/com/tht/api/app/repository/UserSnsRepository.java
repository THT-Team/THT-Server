package com.tht.api.app.repository;

import com.tht.api.app.entity.enums.SNSType;
import com.tht.api.app.entity.user.UserSns;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSnsRepository extends JpaRepository<UserSns, Long> {

    boolean existsBySnsTypeAndSnsUniqueId(final SNSType snsType, final String snsUniqueId);
}
