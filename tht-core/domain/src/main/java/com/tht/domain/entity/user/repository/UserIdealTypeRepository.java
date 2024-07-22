package com.tht.domain.entity.user.repository;


import com.tht.domain.entity.user.UserIdealType;
import com.tht.domain.entity.user.repository.querydsl.UserIdealTypeCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserIdealTypeRepository extends JpaRepository<UserIdealType, Long>,
    UserIdealTypeCustomRepository {

    void deleteAllByUserUuid(final String userUuid);
}
