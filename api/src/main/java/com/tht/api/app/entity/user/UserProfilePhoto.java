package com.tht.api.app.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user_profile_photo")
public class UserProfilePhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @Column(name = "user_uuid")
    private String userUuid;

    @Column(name = "url")
    private String url;

    @Column(name = "priority")
    private Integer priority;

    @Builder(access = AccessLevel.PRIVATE)
    public UserProfilePhoto(final Long idx, final String userUuid, final String url,
        final Integer priority) {

        this.idx = idx;
        this.userUuid = userUuid;
        this.url = url;
        this.priority = priority;
    }

    public static UserProfilePhoto create(final String userUuid, final String url,
        final Integer priority) {
        return UserProfilePhoto.builder()
            .userUuid(userUuid)
            .url(url)
            .priority(priority)
            .build();
    }
}
