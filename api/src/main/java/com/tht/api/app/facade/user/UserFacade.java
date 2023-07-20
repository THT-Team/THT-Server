package com.tht.api.app.facade.user;

import com.tht.api.app.entity.user.User;
import com.tht.api.app.entity.user.UserLocationInfo;
import com.tht.api.app.entity.user.UserProfilePhoto;
import com.tht.api.app.facade.Facade;
import com.tht.api.app.facade.user.request.MainScreenUserInfoRequest;
import com.tht.api.app.facade.user.request.UserReportRequest;
import com.tht.api.app.facade.user.response.MainScreenResponse;
import com.tht.api.app.facade.user.response.MainScreenUserInfoResponse;
import com.tht.api.app.facade.user.response.UserDetailResponse;
import com.tht.api.app.repository.mapper.DailyFallingTimeMapper;
import com.tht.api.app.repository.mapper.IdealTypeMapper;
import com.tht.api.app.repository.mapper.InterestMapper;
import com.tht.api.app.repository.mapper.MainScreenUserInfoMapper;
import com.tht.api.app.service.UserBlockService;
import com.tht.api.app.service.UserDailyFallingService;
import com.tht.api.app.service.UserIdealTypeService;
import com.tht.api.app.service.UserInterestsService;
import com.tht.api.app.service.UserLocationInfoService;
import com.tht.api.app.service.UserProfilePhotoService;
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
    private final UserInterestsService userInterestsService;
    private final UserIdealTypeService userIdealTypeService;
    private final UserLocationInfoService userLocationInfoService;
    private final UserProfilePhotoService userProfilePhotoService;

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

    public UserDetailResponse getUserDetail(final User user) {

        final List<IdealTypeMapper> idealTypeMappers = userIdealTypeService
            .findBy(user.getUserUuid());

        final List<InterestMapper> interestMappers = userInterestsService
            .findBy(user.getUserUuid());

        final List<UserProfilePhoto> profilePhotoMappers = userProfilePhotoService
            .findByUuid(user.getUserUuid());

        final UserLocationInfo userLocationInfo = userLocationInfoService
            .findByUserUuid(user.getUserUuid());

        return UserDetailResponse.of(
            user,
            idealTypeMappers,
            interestMappers,
            profilePhotoMappers,
            userLocationInfo
        );
    }

}
