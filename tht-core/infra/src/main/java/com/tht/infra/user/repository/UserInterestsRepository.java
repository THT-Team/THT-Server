package com.tht.infra.user.repository;


import com.tht.infra.user.UserInterests;
import com.tht.infra.user.repository.querydsl.UserInterestsCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInterestsRepository extends JpaRepository<UserInterests, Long>,
    UserInterestsCustomRepository {

    void deleteAllByUserUuid(final String userUuid);

}
