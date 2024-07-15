package com.tht.infra.user.repository;


import com.tht.infra.user.UserIdealType;
import com.tht.infra.user.repository.querydsl.UserIdealTypeCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserIdealTypeRepository extends JpaRepository<UserIdealType, Long>,
    UserIdealTypeCustomRepository {

    void deleteAllByUserUuid(final String userUuid);
}
