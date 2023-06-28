package com.tht.api.app.facade.user;

import com.tht.api.app.facade.Facade;
import com.tht.api.app.facade.user.group.MainScreenUserInfoResponseGroup;
import com.tht.api.app.facade.user.request.MainScreenUserInfoRequest;
import com.tht.api.app.facade.user.request.UserReportRequest;
import com.tht.api.app.facade.user.response.MainScreenResponse;
import com.tht.api.app.repository.mapper.DailyFallingTimeMapper;
import com.tht.api.app.repository.mapper.MainScreenUserInfoMapper;
import com.tht.api.app.service.UserBlockService;
import com.tht.api.app.service.UserDailyFallingService;
import com.tht.api.app.service.UserReportService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
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

        final Map<String, List<MainScreenUserInfoMapper>> listMap = userDailyFallingService.findAllMatchingFallingUser(
                fallingInfo.get().dailyFallingIdx(),
                request.alreadySeenUserUuidList(),
                request.userDailyFallingCourserIdx(),
                userUuid, request.size()
            )
            .stream()
            .collect(Collectors.groupingBy(MainScreenUserInfoMapper::userUuid));

        return MainScreenResponse.of(
            fallingInfo.get().dailyFallingIdx(),
            fallingInfo.get().endDate(),
            MainScreenUserInfoResponseGroup.of(listMap).responses()
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
