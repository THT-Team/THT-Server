package com.tht.thtadmin.ui.user;

import com.tht.domain.entity.block.UserBlockService;
import com.tht.domain.entity.block.dto.UserBlockDto;
import com.tht.domain.entity.report.dto.UserReportDto;
import com.tht.domain.entity.user.User;
import com.tht.domain.entity.user.service.UserDetailDto;
import com.tht.domain.entity.report.UserReportService;
import com.tht.domain.entity.user.service.UserService;
import com.tht.thtadmin.ui.user.response.UserBlockResponse;
import com.tht.thtadmin.ui.user.response.UserDetailResponse;
import com.tht.thtadmin.ui.user.response.UserReportResponse;
import com.tht.thtadmin.ui.user.response.UserSimpleListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserManageUseCase {

    private final UserService userService;
    private final UserBlockService userBlockService;
    private final UserReportService userReportService;

    public Page<UserSimpleListResponse> getUserList(final String search, final Pageable pageable) {

        final Page<User> pageResult = userService.getSimpleUserPageList(search, pageable);

        final List<UserSimpleListResponse> responses = pageResult
            .getContent()
            .stream()
            .map(user -> UserSimpleListResponse.of(
                user.getUsername(),
                user.getUserUuid(),
                user.getCreatedAt(),
                user.getState())
            )
            .toList();

        return new PageImpl<>(responses, pageable, pageResult.getTotalElements());
    }

    public UserDetailResponse getUserDetail(final String userUuid) {
        final UserDetailDto dto = userService.getDetailForAdmin(userUuid);
        return UserDetailResponse.toResponse(dto);
    }

    public Page<UserBlockResponse> getBlockUserList(final Pageable pageable) {
        final Page<UserBlockDto> blockList = userBlockService.getBlockList(pageable);
        final List<UserBlockResponse> responses = blockList.getContent().stream().map(UserBlockResponse::ofDto).toList();

        return new PageImpl<>(responses, pageable, blockList.getTotalElements());
    }

    public Page<UserReportResponse> getReportUserList(final Pageable pageable) {

        final Page<UserReportDto> reportList = userReportService.getReportList(pageable);
        final List<UserReportResponse> responses = reportList.getContent().stream().map(UserReportResponse::ofDto).toList();

        return new PageImpl<>(responses, pageable, reportList.getTotalElements());
    }
}
