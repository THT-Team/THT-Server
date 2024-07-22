package com.tht.thtapis.facade.user.response;

import com.tht.domain.entity.user.mapper.MainScreenUserInfoMapper;
import com.tht.thtapis.facade.idealtype.response.IdealTypeResponse;
import com.tht.thtapis.facade.interest.response.InterestResponse;
import com.tht.thtcommonutils.utils.ConvertAgeUtils;

import java.time.LocalDate;
import java.util.List;

public record MainScreenUserInfoResponse(
    String username,
    String userUuid,
    int age,
    String address,
    boolean isBirthDay,
    List<IdealTypeResponse> idealTypeResponseList,
    List<InterestResponse> interestResponses,
    List<UserProfilePhotoResponse> userProfilePhotos,
    String introduction,
    long userDailyFallingCourserIdx,
    int distance
) {

    public static MainScreenUserInfoResponse of(final MainScreenUserInfoMapper mapper, final int distance) {

        return new MainScreenUserInfoResponse(
            mapper.username(),
            mapper.userUuid(),
            ConvertAgeUtils.covertBeforeBirthAge(mapper.birthDay()),
            mapper.address(),
            isBirthDay(mapper.birthDay()),
            mapper.idealTypeMapper().stream().map(IdealTypeResponse::of).toList(),
            mapper.interestMapper().stream().map(InterestResponse::of).toList(),
            mapper.userProfilePhotoMapper().stream().map(UserProfilePhotoResponse::of).toList(),
            mapper.introduction(),
            mapper.userDailyFallingIdx(),
            distance
        );
    }

    private static boolean isBirthDay(final LocalDate birthDay) {

        final LocalDate now = LocalDate.now();

        return now.getMonth().equals(birthDay.getMonth())
            && now.getDayOfMonth() == birthDay.getDayOfMonth();
    }
}
