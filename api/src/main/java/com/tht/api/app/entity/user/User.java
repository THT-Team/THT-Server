package com.tht.api.app.entity.user;

import com.tht.api.app.entity.Auditable;
import com.tht.api.app.entity.enums.Gender;
import com.tht.api.app.entity.enums.converter.GenderConverter;
import com.tht.api.app.entity.enums.UserRole;
import com.tht.api.app.entity.enums.converter.UserRoleConverter;
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
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@ToString
public class User extends Auditable {

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

//    @Column(name = "last_login_at")
//    private LocalDateTime lastLoginAt;


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

        return User.builder()
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
    }

    private static String generateUuid() {
        return LocalDateTime.now().getMinute() + UUID.randomUUID().toString();
    }

}
