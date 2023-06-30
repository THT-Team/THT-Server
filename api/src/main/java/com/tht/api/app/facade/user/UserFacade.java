package com.tht.api.app.facade.user;

import com.tht.api.app.facade.Facade;
import com.tht.api.app.facade.user.request.MainScreenUserInfoRequest;
import com.tht.api.app.facade.user.request.UserReportRequest;
import com.tht.api.app.facade.user.response.MainScreenResponse;
import com.tht.api.app.facade.user.response.MainScreenUserInfoResponse;
import com.tht.api.app.repository.mapper.DailyFallingTimeMapper;
import com.tht.api.app.repository.mapper.MainScreenUserInfoMapper;
import com.tht.api.app.service.UserBlockService;
import com.tht.api.app.service.UserDailyFallingService;
import com.tht.api.app.service.UserReportService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Facade
@Transactional
@RequiredArgsConstructor
public class UserFacade {

    private final UserDailyFallingService userDailyFallingService;
    private final UserBlockService userBlockService;
    private final UserReportService userReportService;

    public MainScreenResponse findAllToDayFallingUserList(final String userUuid,
        final MainScreenUserInfoRequest request) {

        final Optional<DailyFallingTimeMapper> fallingInfo = userDailyFallingService
            .findChooseTodayDailyFallingInfo(userUuid);

        if (fallingInfo.isEmpty()) {
            return MainScreenResponse.empty();
        }

        return getMainScreenResponse(userUuid, request, fallingInfo.get());
    }

    private MainScreenResponse getMainScreenResponse(final String userUuid,
        final MainScreenUserInfoRequest request, final DailyFallingTimeMapper fallingInfo) {

        final List<MainScreenUserInfoMapper> list = userDailyFallingService
            .findAllMatchingFallingUser(
                fallingInfo.dailyFallingIdx(),
                request.alreadySeenUserUuidList(),
                request.userDailyFallingCourserIdx(),
                userUuid, request.size()
            );

        return MainScreenResponse.of(
            fallingInfo.dailyFallingIdx(),
            fallingInfo.endDate(),
            list.stream().map(MainScreenUserInfoResponse::of).toList()
        );
    }

    public void report(final String userUuid, final UserReportRequest request) {

        userReportService.create(userUuid, request.reportUserUuid(), request.reason());
        userBlockService.block(userUuid, request.reportUserUuid());
    }

    public void block(final String userUuid, final String blockUserUuid) {
        userBlockService.block(userUuid, blockUserUuid);
    }
}
