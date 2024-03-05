package com.tht.api.app.repository.user.querydsl;

import com.tht.api.app.repository.mapper.IdealTypeMapper;
import java.util.List;

public interface UserIdealTypeCustomRepository {

    List<IdealTypeMapper> findIdealInfoBy(final String userUuid);

}
