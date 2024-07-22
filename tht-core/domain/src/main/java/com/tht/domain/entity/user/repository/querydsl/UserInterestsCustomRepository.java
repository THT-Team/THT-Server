package com.tht.domain.entity.user.repository.querydsl;


import com.tht.domain.entity.interesst.InterestMapper;

import java.util.List;

public interface UserInterestsCustomRepository {

    List<InterestMapper> findInterestInfoBy(String userUuid);

}
