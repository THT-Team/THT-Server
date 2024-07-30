package com.tht.domain.entity.report;

import com.tht.domain.entity.report.dto.UserReportDto;
import com.tht.domain.entity.report.repository.UserReportRepository;
import com.tht.domain.entity.report.repository.mapper.UserReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserReportService {

    private final UserReportRepository repository;

    @Transactional
    public void create(final String userUuid, final String reportUserUuid, final String reason) {

        repository.save(UserReport.create(userUuid, reportUserUuid, reason));
    }

    public Page<UserReportDto> getReportList(final Pageable pageable) {

        final Page<UserReportMapper> reportList = repository.findAllReportList(pageable);
        final List<UserReportDto> result = reportList.getContent().stream()
            .map(UserReportDto::ofMapper)
            .toList();

        return new PageImpl<>(result, pageable, reportList.getTotalElements());
    }
}
