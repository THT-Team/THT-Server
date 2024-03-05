package com.tht.api.app.repository.user.querydsl;

import com.tht.api.app.repository.mapper.InterestMapper;
import java.util.List;

public interface UserInterestsCustomRepository {

    List<InterestMapper> findInterestInfoBy(String userUuid);

}
