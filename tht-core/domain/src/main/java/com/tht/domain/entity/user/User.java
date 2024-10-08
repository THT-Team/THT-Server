package com.tht.domain.entity.user;

import com.tht.domain.entity.user.exception.UserCustomException;
import com.tht.enums.user.Gender;
import com.tht.enums.user.UserFrequency;
import com.tht.enums.user.UserReligion;
import com.tht.enums.user.UserRole;
import com.tht.enums.user.converter.GenderConverter;
import com.tht.enums.user.converter.UserFrequencyConverter;
import com.tht.enums.user.converter.UserReligionConverter;
import com.tht.enums.user.converter.UserRoleConverter;
import com.tht.domain.entity.Auditable;
import com.tht.enums.EntityState;
import com.tht.thtcommonutils.utils.LogWriteUtils;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.regex.Pattern;

@Entity
@Table
@NoArgsConstructor
@Getter
@ToString
@DynamicUpdate
public class User extends Auditable {

    private static final Pattern PHONE_NUMBER_FORMAT = Pattern.compile("^\\d{9,11}$");
    private static final Pattern EMAIL_FORMAT = Pattern.compile(
            "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idx;

    @Column
    private String userUuid;

    @Column
    private String username;

    @Column
    private LocalDate birthDay;

    @Column
    private String phoneNumber;

    @Column
    private String email;

    @Column
    @Lob
    private String introduction;

    @Column
    @Convert(converter = GenderConverter.class)
    private Gender gender;

    @Column
    @Convert(converter = GenderConverter.class)
    private Gender preferGender;

    @Column
    @Convert(converter = UserRoleConverter.class)
    private UserRole userRole;

    @Column
    private int tall;

    @Column
    @Convert(converter = UserFrequencyConverter.class)
    private UserFrequency smoking;

    @Column
    @Convert(converter = UserFrequencyConverter.class)
    private UserFrequency drinking;

    @Column
    @Convert(converter = UserReligionConverter.class)
    private UserReligion religion;

    @Column
    private Boolean isLogin;

    @Builder(access = AccessLevel.PRIVATE)
    public User(final Long idx, final String userUuid, final String username,
                final LocalDate birthDay, final String phoneNumber,
                final String email, final String introduction, final Gender gender,
                final Gender preferGender, final UserRole userRole, final int tall, final UserFrequency smoking,
                final UserFrequency drinking, final UserReligion religion) {

        this.idx = idx;
        this.userUuid = userUuid;
        this.username = username;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.introduction = introduction;
        this.gender = gender;
        this.preferGender = preferGender;
        this.userRole = userRole;
        this.tall = tall;
        this.drinking = drinking;
        this.smoking = smoking;
        this.religion = religion;
        this.isLogin = true;
    }

    public static User createNewUser(final String username, final LocalDate birthDay,
                                     final String phoneNumber, final String email, final String introduction,
                                     final Gender gender, final Gender preferGender, final int tall,
                                     final UserFrequency drinking, final UserFrequency smoking, final UserReligion religion) {

        validPhoneNumberFormat(phoneNumber);
        validEmailFormat(email);

        final User user = User.builder()
                .username(username)
                .userUuid(generateUuid())
                .birthDay(birthDay)
                .phoneNumber(phoneNumber)
                .email(email)
                .introduction(introduction)
                .gender(gender)
                .preferGender(preferGender)
                .userRole(UserRole.NORMAL)
                .tall(tall)
                .drinking(drinking)
                .smoking(smoking)
                .religion(religion)
                .build();

        LogWriteUtils.createInfo(user);

        return user;
    }

    private static void validPhoneNumberFormat(final String phoneNumber) {

        if (!PHONE_NUMBER_FORMAT.matcher(phoneNumber).matches()) {
            throw UserCustomException.noneValidPhoneNumberFormat();
        }
    }

    private static void validEmailFormat(final String email) {

        if (!EMAIL_FORMAT.matcher(email).matches()) {
            throw UserCustomException.noneValidEmailFormat();
        }
    }

    private static String generateUuid() {
        return LocalDateTime.now().getMinute() + UUID.randomUUID().toString();
    }

    public void updatePhoneNumber(final String phoneNumber) {

        validPhoneNumberFormat(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public void updateEmail(final String email) {
        validEmailFormat(email);
        this.email = email;
    }

    public void updateName(final String updateNickName) {
        this.username = updateNickName;
    }

    public boolean isEqualsName(final String updateNickName) {
        return username.equals(updateNickName);
    }

    public void updateIntroduction(final String introduction) {
        this.introduction = introduction;
    }

    public void updatePreferGender(final Gender gender) {
        this.preferGender = gender;
    }

    public void accountWithdrawal() {
        this.state = EntityState.WITHDRAW_REQUEST;
    }

    public void logout() {
        this.isLogin = false;
    }

    public void login() {
        this.isLogin = true;
    }

    public void activate() {
        this.state = EntityState.ACTIVE;
    }

    public void inActivate() {
        this.state = EntityState.INACTIVE;
    }
}
