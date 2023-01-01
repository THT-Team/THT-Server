package com.tht.api.app.entity;

import com.tht.api.enums.Gender;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

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
    @Enumerated
    private Gender gender;

    @Column(name = "prefer_gender")
    @Enumerated
    private Gender preferGender;

    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;

    @Builder
    public User(String username, String passwordHash, String phone, Boolean isPhoneVerified, String email, Boolean isEmailVerified, LocalDateTime emailVerifiedAt, String introduction, Gender gender, Gender preferGender) {
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
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(passwordHash, user.passwordHash) && Objects.equals(email, user.email) && Objects.equals(isEmailVerified, user.isEmailVerified) && Objects.equals(emailVerifiedAt, user.emailVerifiedAt) && Objects.equals(introduction, user.introduction) && gender == user.gender && preferGender == user.preferGender && Objects.equals(lastLoginAt, user.lastLoginAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, passwordHash, email, isEmailVerified, emailVerifiedAt, introduction, gender, preferGender, lastLoginAt);
    }

    @Override
    public String toString() {
        return "User{" +
                "userUuid=" + id +
                ", username='" + username + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", email='" + email + '\'' +
                ", isEmailVerified=" + isEmailVerified +
                ", emailVerifiedAt=" + emailVerifiedAt +
                ", introduction='" + introduction + '\'' +
                ", gender=" + gender +
                ", preferGender=" + preferGender +
                ", lastLoginAt=" + lastLoginAt +
                '}';
    }
}
