package com.tht.thtadmin.ui.user.response;

import com.tht.domain.entity.user.UserAgreement;
import com.tht.domain.entity.user.UserProfilePhoto;
import com.tht.domain.entity.user.service.UserDetailDto;
import com.tht.enums.agreement.AgreementCategory;
import com.tht.enums.user.Gender;
import com.tht.enums.user.SNSType;
import com.tht.enums.user.UserFrequency;
import com.tht.enums.user.UserReligion;

import java.util.*;

public record UserDetailResponse(
    String phoneNumber,
    String username,
    String birthDay,
    String email,
    List<SNSType> snsSignUpList,
    Map<AgreementCategory, Boolean> serviceAgreeList,
    Gender gender,
    Gender preferGender,
    Map<Integer, String> profileUrl,
    int tall,
    UserFrequency drinkStatus,
    UserReligion religion,
    UserFrequency smokingStatus,
    String userLocation,
    List<String> interests,
    List<String> idealTypes

) {

    public static UserDetailResponse toResponse(final UserDetailDto dto) {
        return new UserDetailResponse(
            dto.phoneNumber(),
            dto.username(),
            dto.birthDay(),
            dto.email(),
            dto.snsSignUpList().stream().toList(),
            convertToServiceAgreeMap(dto.userAgreement()),
            dto.gender(),
            dto.preferGender(),
            convertToUserProfilePhotosMap(dto.profileUrl()),
            dto.tall(),
            dto.drinkStatus(),
            dto.religion(),
            dto.smokingStatus(),
            dto.userLocation(),
            dto.interests().stream().toList(),
            dto.idealTypes().stream().toList()
        );
    }

    private static Map<Integer, String> convertToUserProfilePhotosMap(final Set<UserProfilePhoto> photoSet) {

        Map<Integer, String> map = new HashMap<>();

        for (UserProfilePhoto photo : photoSet) {
            map.put(photo.getPriority(), photo.getUrl());
        }

        return map;
    }

    private static Map<AgreementCategory, Boolean> convertToServiceAgreeMap(final UserAgreement userAgreements) {

        Map<AgreementCategory, Boolean> serviceAgreeMap = new EnumMap<>(AgreementCategory.class);

        for (AgreementCategory agreementCategory : AgreementCategory.values()) {
            serviceAgreeMap.put(agreementCategory, userAgreements.getAgreementStatus(agreementCategory));
        }
        return serviceAgreeMap;
    }
}
