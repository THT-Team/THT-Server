package com.tht.infra.user.repository.querydsl;


import com.tht.enums.user.SNSType;
import com.tht.infra.user.mapper.UserSnsMapper;

import java.util.List;
import java.util.Optional;

public interface UserSNSRepositoryCustom {

    Optional<List<UserSnsMapper>> findAllByPhoneNumber(final String phoneNumber);

    Optional<String> findUserUuidBySnsIdKey(final SNSType snsType, final String snsUniqueId);
}
