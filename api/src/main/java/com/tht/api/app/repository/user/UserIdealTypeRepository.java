package com.tht.api.app.repository.user;


import com.tht.api.app.entity.user.UserIdealType;
import com.tht.api.app.repository.user.querydsl.UserIdealTypeCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserIdealTypeRepository extends JpaRepository<UserIdealType, Long>,
    UserIdealTypeCustomRepository {

}
