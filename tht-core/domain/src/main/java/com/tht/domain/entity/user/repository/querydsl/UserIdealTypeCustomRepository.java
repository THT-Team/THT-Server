package com.tht.domain.entity.user.repository.querydsl;


import com.tht.domain.entity.idealtype.IdealTypeMapper;

import java.util.List;

public interface UserIdealTypeCustomRepository {

    List<IdealTypeMapper> findIdealInfoBy(final String userUuid);

}
