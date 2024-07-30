package com.tht.domain.entity.user.repository;

import com.tht.domain.entity.user.UserWithDrawLog;
import com.tht.domain.entity.user.repository.querydsl.UserWithDrawCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWithDrawLogRepository extends JpaRepository<UserWithDrawLog, Long>, UserWithDrawCustomRepository {

}
