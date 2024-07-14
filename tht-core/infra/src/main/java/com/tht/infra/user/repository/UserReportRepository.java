package com.tht.infra.user.repository;

import com.tht.infra.user.UserReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReportRepository extends JpaRepository<UserReport, Long> {

}
