package com.tht.thtadmin.ui.user;

import com.tht.domain.entity.block.UserBlockService;
import com.tht.domain.entity.block.dto.UserBlockDto;
import com.tht.domain.entity.report.UserReportService;
import com.tht.domain.entity.report.dto.UserReportDto;
import com.tht.domain.entity.user.service.UserService;
import com.tht.domain.entity.user.service.dto.UserDetailDto;
import com.tht.domain.entity.user.service.dto.UserListDto;
import com.tht.domain.entity.user.service.dto.WithDrawUserDto;
import com.tht.thtadmin.ui.user.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

        final Page<UserListDto> pageResult = userService.getSimpleUserPageList(search, pageable);

        final List<UserSimpleListResponse> responses = pageResult
            .getContent()
            .stream()
            .map(dto -> UserSimpleListResponse.of(
                    dto.username(),
                    dto.profilePhotoUrl(),
                    dto.userUuid(),
                    dto.createdAt(),
                    dto.userSate()
                )
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

    public Page<WithDrawUserResponse> getWithDrawList(final Pageable pageable) {

        final Page<WithDrawUserDto> reportList = userService.getWithDrawUserLog(pageable);
        final List<WithDrawUserResponse> responses = reportList.getContent().stream().map(WithDrawUserResponse::ofDto).toList();

        return new PageImpl<>(responses, pageable, reportList.getTotalElements());

    }

    @Transactional
    public void activateUserStatus(final String userUuid) {

        userService.changeActivateStatus(userUuid);
    }

    @Transactional
    public void deactivateUserStatus(final String userUuid) {

        userService.changeInActivateStatus(userUuid);
    }
}
