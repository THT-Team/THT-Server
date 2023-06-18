package com.tht.api.app.repository.user;

import com.tht.api.app.entity.enums.SNSType;
import com.tht.api.app.entity.user.UserSns;
import com.tht.api.app.repository.user.querydsl.UserSNSRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSnsRepository extends JpaRepository<UserSns, Long>, UserSNSRepositoryCustom {

    boolean existsBySnsTypeAndSnsUniqueId(final SNSType snsType, final String snsUniqueId);

    boolean existsByUserUuidAndSnsTypeOrSnsTypeAndSnsUniqueId(final String userUuid,
        final SNSType snsType, final SNSType snsType1, final String snsUniqueId);
}
