package com.tht.domain.entity.user.repository;


import com.tht.domain.entity.user.UserInterests;
import com.tht.domain.entity.user.repository.querydsl.UserInterestsCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInterestsRepository extends JpaRepository<UserInterests, Long>,
    UserInterestsCustomRepository {

    void deleteAllByUserUuid(final String userUuid);

}
