package com.tht.domain.entity.report.repository;

import com.tht.domain.entity.report.repository.mapper.UserReportMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserReportCustomRepository {

    Page<UserReportMapper> findAllReportList(final Pageable pageable);
}
