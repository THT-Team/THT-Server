package com.tht.thtapis.facade.user;

import com.tht.domain.entity.block.UserBlockService;
import com.tht.domain.entity.report.UserReportService;
import com.tht.enums.user.Gender;
import com.tht.domain.entity.chat.service.ChatRoomUserService;
import com.tht.domain.entity.dailyfalling.mapper.DailyFallingTimeMapper;
import com.tht.domain.entity.idealtype.IdealTypeMapper;
import com.tht.domain.entity.idealtype.IdealTypeService;
import com.tht.domain.entity.interesst.InterestMapper;
import com.tht.domain.entity.interesst.InterestService;
import com.tht.domain.entity.user.User;
import com.tht.domain.entity.user.UserAgreement;
import com.tht.domain.entity.user.UserLocationInfo;
import com.tht.domain.entity.user.UserProfilePhoto;
import com.tht.domain.entity.user.mapper.MainScreenUserInfoMapper;
import com.tht.domain.entity.user.service.*;
import com.tht.thtapis.facade.Facade;
import com.tht.thtapis.facade.user.request.*;
import com.tht.thtapis.facade.user.response.MainScreenResponse;
import com.tht.thtapis.facade.user.response.MainScreenUserInfoResponse;
import com.tht.thtapis.facade.user.response.UserDetailResponse;
import com.tht.thtapis.security.TokenDto;
import com.tht.thtapis.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    private final UserAlarmAgreementService userAlarmAgreementService;
    private final UserAgreementService userAgreementService;
    private final UserFriendService userFriendService;
    private final ChatRoomUserService chatRoomUserService;
    private final UserDeviceKeyService userDeviceKeyService;

    public MainScreenResponse findAllToDayFallingUserList(final User user,
                                                          final MainScreenUserInfoRequest request) {

        final Optional<DailyFallingTimeMapper> fallingInfo = userDailyFallingService
            .findChooseTodayDailyFallingInfo(user.getUserUuid());

        if(fallingInfo.isEmpty()) {
            return MainScreenResponse.empty();
        }

        final DailyFallingTimeMapper dailyFallingTimeMapper = fallingInfo.get();
        final Long dailyFallingIdx = dailyFallingTimeMapper.dailyFallingIdx();

        long fallingUsers = userDailyFallingService.getCountFallingUsers(dailyFallingIdx);

        if(fallingUsers == 0) {
            return MainScreenResponse.choiceUsersEmpty(dailyFallingIdx, dailyFallingTimeMapper.endDate());
        }

        return getMainScreenResponse(user, request, dailyFallingTimeMapper);

    }

    private MainScreenResponse getMainScreenResponse(final User user,
                                                     final MainScreenUserInfoRequest request, final DailyFallingTimeMapper fallingInfo) {

        final List<MainScreenUserInfoMapper> list = userDailyFallingService
            .findAllMatchingFallingUser(
                fallingInfo.dailyFallingIdx(),
                request.userDailyFallingCourserIdx(),
                user.getUserUuid(),
                user.getGender(),
                user.getPreferGender(),
                request.getSize()
            );

        final UserLocationInfo userLocationInfo = userLocationInfoService.findByUserUuid(user.getUserUuid());

        return MainScreenResponse.of(
            fallingInfo.dailyFallingIdx(),
            fallingInfo.endDate(),
            list.stream().map(mapper -> MainScreenUserInfoResponse.of(mapper, userLocationInfo.getDistanceBetween(mapper.lat(), mapper.lon()))).toList(),
            request.getSize()
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
        chatRoomUserService.outOfBlockChatRoom(userUuid, blockUserUuid);
    }

    public UserDetailResponse getUserDetail(final String userUuid) {

        final User user = userService.findByUserUuidForAuthToken(userUuid);

        final List<IdealTypeMapper> idealTypeMappers = userIdealTypeService
            .findBy(user.getUserUuid());

        final List<InterestMapper> interestMappers = userInterestsService
            .findBy(user.getUserUuid());

        final List<UserProfilePhoto> profilePhotoMappers = userProfilePhotoService
            .findByUuid(user.getUserUuid());

        final UserLocationInfo userLocationInfo = userLocationInfoService
            .findByUserUuid(user.getUserUuid());

        final UserAgreement userAgreement = userAgreementService.findByUserUuid(user.getUserUuid());

        return UserDetailResponse.of(
            user,
            idealTypeMappers,
            interestMappers,
            profilePhotoMappers,
            userLocationInfo,
            userAgreement
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
    public TokenDto updateNickName(final User user, final String updateNickName) {

        User updateUserInfo = userService.updateName(user, updateNickName);

        return tokenProvider.generateJWT(updateUserInfo);
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

    @Transactional
    public void updatePersonalAlarmAgree(final String userUuid,
                                         final UserAlarmAgreementModifyRequest request) {

        userAgreementService.modifyMarketingAgree(userUuid, request.marketingAlarm());
        userAlarmAgreementService.update(userUuid, request.newMatchSuccessAlarm(),
            request.likeMeAlarm(), request.newConversationAlarm(),
            request.talkAlarm());
    }

    @Transactional
    public void withDraw(final User user, final UserWithDrawRequest request) {

        userService.withDraw(user, request.reason(), request.feedBack());
    }

    public int getFriendCount(final String userUuid) {
        return userFriendService.getFriendCount(userUuid);
    }

    @Transactional
    public int updateFriendContactList(final String userUuid, final List<UserFriendContactInfo> contacts) {

        return userFriendService.update(userUuid, contacts.stream().map(UserFriendContactInfo::toContactDto).toList());
    }

    @Transactional
    public void updateSingleUserAgreement(final User user, final UserAgreementUpdateRequest request) {

        userAgreementService.updateSingleAgreement(user.getUserUuid(), request.agreementName(), request.value());
    }

    @Transactional
    public void updateDeviceKey(final String userUuid, final String deviceKey) {

        userDeviceKeyService.update(userUuid, deviceKey);
    }
}
