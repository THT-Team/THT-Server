package com.tht.infra.user.repository.querydsl;


import com.tht.infra.idealtype.IdealTypeMapper;

import java.util.List;

public interface UserIdealTypeCustomRepository {

    List<IdealTypeMapper> findIdealInfoBy(final String userUuid);

}
