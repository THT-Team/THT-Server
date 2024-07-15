package com.tht.infra.user.repository;

import com.tht.infra.user.UserSns;
import com.tht.infra.user.enums.SNSType;
import com.tht.infra.user.repository.querydsl.UserSNSRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSnsRepository extends JpaRepository<UserSns, Long>, UserSNSRepositoryCustom {

    boolean existsBySnsTypeAndSnsUniqueId(final SNSType snsType, final String snsUniqueId);

    boolean existsByUserUuidAndSnsTypeOrSnsTypeAndSnsUniqueId(final String userUuid,
        final SNSType snsType, final SNSType snsType1, final String snsUniqueId);
}
