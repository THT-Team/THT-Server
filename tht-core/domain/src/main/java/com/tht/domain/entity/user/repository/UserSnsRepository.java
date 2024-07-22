package com.tht.domain.entity.user.repository;

import com.tht.domain.entity.user.UserSns;
import com.tht.enums.user.SNSType;
import com.tht.domain.entity.user.repository.querydsl.UserSNSRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSnsRepository extends JpaRepository<UserSns, Long>, UserSNSRepositoryCustom {

    boolean existsBySnsTypeAndSnsUniqueId(final SNSType snsType, final String snsUniqueId);

    boolean existsByUserUuidAndSnsTypeOrSnsTypeAndSnsUniqueId(final String userUuid,
        final SNSType snsType, final SNSType snsType1, final String snsUniqueId);
}
