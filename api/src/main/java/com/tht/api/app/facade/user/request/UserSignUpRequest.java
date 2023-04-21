package com.tht.api.app.facade.user.request;

import com.tht.api.app.entity.user.Gender;
import com.tht.api.app.entity.user.User;
import com.tht.api.app.entity.user.UserAgreement;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class UserSignUpRequest{

    @NotEmpty
    String username;
    @NotEmpty
    String password;
    @NotEmpty
    String email;
    String introduction;
    @NotEmpty
    String phoneNumber;
    @NotEmpty
    Gender gender;
    @NotEmpty
    Gender preferGender;
    @NotEmpty
    String deviceKey;
    @NotNull
    UserAgreementRequest agreement;
    //위치정보


    public UserSignUpRequest(final String username, final String password, final String email,
        final String introduction, final String phoneNumber, final String gender, final String preferGender,
        final String deviceKey, final UserAgreementRequest agreement) {

        agreement.valid();

        this.username = username;
        this.password = password;
        this.email = email;
        this.introduction = introduction;
        this.phoneNumber = phoneNumber;
        this.gender = Gender.toConverter(gender);
        this.preferGender = Gender.toConverter(preferGender);
        this.deviceKey = deviceKey;
        this.agreement = agreement;
    }

    public User toEntity() {
        return User.createNewUser(username, password, phoneNumber,
            email, introduction, gender, preferGender);
    }

    public UserAgreement getAgreementEntity() {
        return this.agreement.toEntity();
    }
}
