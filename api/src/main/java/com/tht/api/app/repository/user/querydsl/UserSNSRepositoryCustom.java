package com.tht.api.app.repository.user.querydsl;

import com.tht.api.app.entity.enums.SNSType;
import com.tht.api.app.repository.mapper.UserSnsMapper;
import java.util.List;
import java.util.Optional;

public interface UserSNSRepositoryCustom {

    Optional<List<UserSnsMapper>> findAllByPhoneNumber(final String phoneNumber);

    Optional<String> findUserUuidBySnsIdKey(final SNSType snsType, final String snsUniqueId);
}
