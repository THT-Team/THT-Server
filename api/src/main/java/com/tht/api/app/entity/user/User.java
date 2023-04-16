package com.tht.api.app.entity.user;

import com.tht.api.app.entity.Auditable;
import com.tht.api.app.facade.user.request.UserSignUpRequest;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
public final class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @Column(name = "username")
    private String username;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "phone")
    private String phone;

    @Column(name = "is_phone_verified")
    private Boolean isPhoneVerified;

    @Column(name = "email")
    private String email;

    @Column(name = "is_email_verified")
    private Boolean isEmailVerified;

    @Column(name = "email_verified_at")
    private LocalDateTime emailVerifiedAt;

    @Column(name = "introduction")
    @Lob
    private String introduction;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "prefer_gender")
    @Convert(converter = GenderConverter.class)
    @Enumerated(EnumType.STRING)
    private Gender preferGender;

    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;

    @Builder
    public User(final String username, final String passwordHash, final String phone, final Boolean isPhoneVerified, final String email, final Boolean isEmailVerified, final LocalDateTime emailVerifiedAt, final String introduction, final Gender gender, final Gender preferGender, final LocalDateTime lastLoginAt) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.phone = phone;
        this.isPhoneVerified = isPhoneVerified;
        this.email = email;
        this.isEmailVerified = isEmailVerified;
        this.emailVerifiedAt = emailVerifiedAt;
        this.introduction = introduction;
        this.gender = gender;
        this.preferGender = preferGender;
        this.lastLoginAt = lastLoginAt;
    }
}
