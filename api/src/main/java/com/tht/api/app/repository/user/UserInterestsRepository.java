package com.tht.api.app.repository.user;


import com.tht.api.app.entity.user.UserInterests;
import com.tht.api.app.repository.user.querydsl.UserInterestsCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInterestsRepository extends JpaRepository<UserInterests, Long>,
    UserInterestsCustomRepository {
}
