package com.tht.api.app.service;

import com.tht.api.app.entity.user.UserReport;
import com.tht.api.app.repository.user.UserReportRepository;
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
