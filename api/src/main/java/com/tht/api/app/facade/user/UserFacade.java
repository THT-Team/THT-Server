package com.tht.api.app.facade.user;

import com.tht.api.app.config.security.TokenProvider;
import com.tht.api.app.entity.enums.Gender;
import com.tht.api.app.entity.user.User;
import com.tht.api.app.entity.user.UserLocationInfo;
import com.tht.api.app.entity.user.UserProfilePhoto;
import com.tht.api.app.facade.Facade;
import com.tht.api.app.facade.user.request.MainScreenUserInfoRequest;
import com.tht.api.app.facade.user.request.ModifiedIdealTypeRequest;
import com.tht.api.app.facade.user.request.ModifiedInterestsRequest;
import com.tht.api.app.facade.user.request.UserLocationRequest;
import com.tht.api.app.facade.user.request.UserProfilePhotoRequest;
import com.tht.api.app.facade.user.request.UserReportRequest;
import com.tht.api.app.facade.user.response.MainScreenResponse;
import com.tht.api.app.facade.user.response.MainScreenUserInfoResponse;
import com.tht.api.app.facade.user.response.UserDetailResponse;
import com.tht.api.app.facade.user.response.UserLoginResponse;
import com.tht.api.app.repository.mapper.DailyFallingTimeMapper;
import com.tht.api.app.repository.mapper.IdealTypeMapper;
import com.tht.api.app.repository.mapper.InterestMapper;
import com.tht.api.app.repository.mapper.MainScreenUserInfoMapper;
import com.tht.api.app.service.IdealTypeService;
import com.tht.api.app.service.InterestService;
import com.tht.api.app.service.UserBlockService;
import com.tht.api.app.service.UserDailyFallingService;
import com.tht.api.app.service.UserIdealTypeService;
import com.tht.api.app.service.UserInterestsService;
import com.tht.api.app.service.UserLocationInfoService;
import com.tht.api.app.service.UserProfilePhotoService;
import com.tht.api.app.service.UserReportService;
import com.tht.api.app.service.UserService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Facade
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserFacade {

    private final UserDailyFallingService userDailyFallingService;
    private final UserBlockService userBlockService;
    private final UserReportService userReportService;
    private final UserInterestsService userInterestsService;
    private final UserIdealTypeService userIdealTypeService;
    private final UserLocationInfoService userLocationInfoService;
    private final UserProfilePhotoService userProfilePhotoService;
    private final UserService userService;
    private final InterestService interestService;
    private final IdealTypeService idealTypeService;
    private final TokenProvider tokenProvider;


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

    @Transactional
    public void report(final String userUuid, final UserReportRequest request) {

        userReportService.create(userUuid, request.reportUserUuid(), request.reason());
        userBlockService.block(userUuid, request.reportUserUuid());
    }

    @Transactional
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

    @Transactional
    public void updatePhoneNumber(final User user, final String phoneNumber) {

        userService.updatePhoneNumber(user.getUserUuid(), phoneNumber);
    }

    @Transactional
    public void updateEmail(final User user, final String email) {
        userService.updateEmail(user.getUserUuid(), email);
    }

    @Transactional
    public void modifiedInterests(final String userUuid, final ModifiedInterestsRequest request) {

        interestService.existIn(request.interestList());
        userInterestsService.update(userUuid, request.interestList());
    }

    @Transactional
    public void modifiedIdealType(final String userUuid, final ModifiedIdealTypeRequest request) {

        idealTypeService.existIn(request.idealTypeList());
        userIdealTypeService.update(userUuid, request.idealTypeList());
    }

    @Transactional
    public void updateLocation(final String userUuid, final UserLocationRequest request) {

        userLocationInfoService.update(userUuid, request.address(), request.regionCode(),
            request.lat(), request.lon());
    }

    @Transactional
    public UserLoginResponse updateNickName(final User user, final String updateNickName) {

        User updateUserInfo = userService.updateName(user, updateNickName);

        return tokenProvider.generateJWT(updateUserInfo).toLoginResponse();
    }

    @Transactional
    public void updateIntroduction(final User user, final String introduction) {
        userService.updateIntroduction(user, introduction);
    }

    @Transactional
    public void updateUserProfilePhoto(
        final String userUuid,
        final List<UserProfilePhotoRequest> userProfilePhotoList) {

        userProfilePhotoService.updateAll(
            userUuid,
            userProfilePhotoList.stream()
                .map(userProfilePhotoRequest -> userProfilePhotoRequest.toEntity(userUuid))
                .toList()
        );
    }

    @Transactional
    public void updatePreferGender(final User user, final Gender gender) {
        userService.updatePreferGender(user, gender);
    }
}
