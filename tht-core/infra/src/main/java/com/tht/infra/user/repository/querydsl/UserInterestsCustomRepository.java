package com.tht.infra.user.repository.querydsl;


import com.tht.infra.interesst.InterestMapper;

import java.util.List;

public interface UserInterestsCustomRepository {

    List<InterestMapper> findInterestInfoBy(String userUuid);

}
