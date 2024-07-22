package com.tht.domain.entity.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Getter
@NoArgsConstructor
@Table
public class UserProfilePhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idx;

    @Column
    private String userUuid;

    @Column
    private String url;

    @Column
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
