package com.tht.api.app.entity.user;

import com.tht.api.app.config.utils.LogWriteUtils;
import com.tht.api.app.entity.Auditable;
import com.tht.api.app.entity.enums.Gender;
import com.tht.api.app.entity.enums.UserRole;
import com.tht.api.app.entity.enums.converter.GenderConverter;
import com.tht.api.app.entity.enums.converter.UserRoleConverter;
import com.tht.api.exception.custom.UserCustomException;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "user")
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
    @Column(name = "idx")
    private Long idx;

    @Column(name = "user_uuid")
    private String userUuid;

    @Column(name = "username")
    private String username;

    @Column(name = "birth_day")
    private LocalDate birthDay;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "introduction")
    @Lob
    private String introduction;

    @Column(name = "gender")
    @Convert(converter = GenderConverter.class)
    private Gender gender;

    @Column(name = "prefer_gender")
    @Convert(converter = GenderConverter.class)
    private Gender preferGender;

    @Column(name = "user_role")
    @Convert(converter = UserRoleConverter.class)
    private UserRole userRole;

    @Builder(access = AccessLevel.PRIVATE)
    public User(final Long idx, final String userUuid, final String username,
        final LocalDate birthDay, final String phoneNumber,
        final String email, final String introduction, final Gender gender,
        final Gender preferGender, final UserRole userRole) {

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
    }

    public static User createNewUser(final String username, final LocalDate birthDay,
        final String phoneNumber, final String email, final String introduction,
        final Gender gender, final Gender preferGender) {

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
}
