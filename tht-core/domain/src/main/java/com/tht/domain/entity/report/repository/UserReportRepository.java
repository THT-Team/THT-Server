package com.tht.domain.entity.report.repository;

import com.tht.domain.entity.report.UserReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReportRepository extends JpaRepository<UserReport, Long>, UserReportCustomRepository {

}
