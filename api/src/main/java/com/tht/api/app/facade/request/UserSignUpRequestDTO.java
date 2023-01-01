package com.tht.api.app.facade.request;

import com.tht.api.enums.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserSignUpRequestDTO {
    private String username;

    private String password;

    private String phone;

    private String email;

    private Gender gender;

    private Gender preferGender;

    @Builder
    public UserSignUpRequestDTO(String username, String password, String phone, String email, Gender gender, Gender preferGender) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.preferGender = preferGender;
    }
}
