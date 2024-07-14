package com.tht.infra.user.repository;

import com.tht.infra.user.UserWithDrawLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWithDrawLogRepository extends JpaRepository<UserWithDrawLog, Long> {

}
