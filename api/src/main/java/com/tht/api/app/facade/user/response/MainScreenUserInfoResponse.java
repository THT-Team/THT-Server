package com.tht.api.app.facade.user.response;

import com.tht.api.app.facade.idealtype.response.IdealTypeResponse;
import com.tht.api.app.facade.interest.response.InterestResponse;
import com.tht.api.app.repository.mapper.MainScreenUserInfoMapper;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

public record MainScreenUserInfoResponse(
    String username,
    String userUuid,
    int age,
    String address,
    boolean isBirthDay,
    HashSet<IdealTypeResponse> idealTypeResponseList,
    HashSet<InterestResponse> interestResponses,
    HashSet<UserProfilePhotoResponse> userProfilePhotos,
    String introduction,
    long userDailyFallingCourserIdx
) {

    public static MainScreenUserInfoResponse of(final List<MainScreenUserInfoMapper> mapperList) {

        if (mapperList.isEmpty()) {
            return null;
        }

        MainScreenUserInfoMapper base = mapperList.get(0);

        return new MainScreenUserInfoResponse(
            base.username(),
            base.userUuid(),
            covertAge(base.birthDay()),
            base.address(),
            isBirthDay(base.birthDay()),
            new HashSet<>(mapperList.stream().map(MainScreenUserInfoMapper::idealTypeMapper)
                .map(IdealTypeResponse::of).toList()),
            new HashSet<>(mapperList.stream().map(MainScreenUserInfoMapper::interestMapper)
                .map(InterestResponse::of).toList()),
            new HashSet<>(mapperList.stream().map(MainScreenUserInfoMapper::userProfilePhotoMapper)
                .map(UserProfilePhotoResponse::of).toList()),
            base.introduction(),
            base.userDailyFallingIdx()
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
