package com.tht.infra.user.service;

import com.tht.infra.user.UserReport;
import com.tht.infra.user.repository.UserReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserReportService {

    private final UserReportRepository repository;

    public void create(final String userUuid, final String reportUserUuid, final String reason) {

        repository.save(UserReport.create(userUuid, reportUserUuid, reason));
    }
}
