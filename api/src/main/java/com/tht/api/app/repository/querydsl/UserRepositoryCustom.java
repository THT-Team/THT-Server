package com.tht.api.app.repository.querydsl;

import com.tht.api.app.repository.mapper.UserSnsMapper;
import java.util.List;
import java.util.Optional;

public interface UserRepositoryCustom {

    Optional<List<UserSnsMapper>> findAllByPhoneNumber(final String phoneNumber);

}
