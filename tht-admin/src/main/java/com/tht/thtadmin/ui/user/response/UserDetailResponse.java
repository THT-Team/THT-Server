package com.tht.thtadmin.ui.user.response;

import com.tht.enums.agreement.AgreementCategory;
import com.tht.enums.user.Gender;
import com.tht.enums.user.SNSType;
import com.tht.enums.user.UserFrequency;
import com.tht.enums.user.UserReligion;

import java.util.List;
import java.util.Map;

public record UserDetailResponse(
    String phoneNumber,
    String username,
    String birthDay,
    String email,
    List<SNSType> snsSignUpList,
    Map<AgreementCategory, Boolean> serviceAgreeList,
    Gender Gender,
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
}
