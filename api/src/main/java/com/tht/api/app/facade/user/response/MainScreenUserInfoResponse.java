package com.tht.api.app.facade.user.response;

import com.tht.api.app.facade.idealtype.response.IdealTypeResponse;
import com.tht.api.app.facade.interest.response.InterestResponse;
import com.tht.api.app.repository.mapper.MainScreenUserInfoMapper;
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
    long userDailyFallingCourserIdx
) {

    public static MainScreenUserInfoResponse of(final MainScreenUserInfoMapper mapper) {

        return new MainScreenUserInfoResponse(
            mapper.username(),
            mapper.userUuid(),
            covertAge(mapper.birthDay()),
            mapper.address(),
            isBirthDay(mapper.birthDay()),
            mapper.idealTypeMapper().stream().map(IdealTypeResponse::of).toList(),
            mapper.interestMapper().stream().map(InterestResponse::of).toList(),
            mapper.userProfilePhotoMapper().stream().map(UserProfilePhotoResponse::of).toList(),
            mapper.introduction(),
            mapper.userDailyFallingIdx()
        );
    }

    private static int covertAge(final LocalDate birthDay) {
        final int simpleAge = LocalDate.now().minusYears(birthDay.getYear()).getYear();

        return simpleAge - minusBeforeBirthDayAge(birthDay, simpleAge);
    }

    private static int minusBeforeBirthDayAge(final LocalDate birthDay, final int simpleAge) {

        final LocalDate now = LocalDate.now();

        if (birthDay.plusDays(simpleAge).isAfter(now)) {
            return 1;
        }
        return 0;
    }

    private static boolean isBirthDay(final LocalDate birthDay) {

        final LocalDate now = LocalDate.now();

        return now.getMonth().equals(birthDay.getMonth())
            && now.getDayOfMonth() == birthDay.getDayOfMonth();
    }
}
