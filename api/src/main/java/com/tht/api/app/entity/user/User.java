package com.tht.api.app.entity.user;

import com.tht.api.app.entity.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
public final class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @Column(name = "user_uuid")
    private String userUuid;

    @Column(name = "username")
    private String username;

    @Column(name = "password_hash")
    private String passwordHash;

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
        final String passwordHash, final String phoneNumber,
        final String email, final String introduction, final Gender gender,
        final Gender preferGender, final UserRole userRole) {

        this.idx = idx;
        this.userUuid = userUuid;
        this.username = username;
        this.passwordHash = passwordHash;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.introduction = introduction;
        this.gender = gender;
        this.preferGender = preferGender;
        this.userRole = userRole;
    }

    public static User createNewUser(final String username, final String passwordHash,
        final String phoneNumber, final String email, final String introduction,
        final Gender gender, final Gender preferGender) {

        return User.builder()
            .username(username)
            .userUuid(generateUuid())
            .passwordHash(passwordHash)
            .phoneNumber(phoneNumber)
            .email(email)
            .introduction(introduction)
            .gender(gender)
            .preferGender(preferGender)
            .userRole(UserRole.NORMAL)
            .build();
    }

    private static String generateUuid() {
        return LocalDateTime.now() + UUID.randomUUID().toString();
    }

}
