package com.tht.infra.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@ToString
@Table
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@DynamicUpdate
public class UserAlarmAgreement {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String userUuid;

    @Column
    private boolean newMatchSuccessAlarm;

    @Column
    private boolean likeMeAlarm;

    @Column
    private boolean newConversationAlarm;

    @Column
    private boolean talkAlarm;

    @LastModifiedDate
    @Column
    private LocalDateTime lastModifiedAt;

    @Builder(access = AccessLevel.PRIVATE)
    private UserAlarmAgreement(String userUuid, boolean newMatchSuccessAlarm, boolean likeMeAlarm,
        boolean newConversationAlarm, boolean talkAlarm) {

        this.userUuid = userUuid;
        this.newMatchSuccessAlarm = newMatchSuccessAlarm;
        this.likeMeAlarm = likeMeAlarm;
        this.newConversationAlarm = newConversationAlarm;
        this.talkAlarm = talkAlarm;
    }

    public static UserAlarmAgreement create(final String userUuid) {

        return UserAlarmAgreement.builder()
            .userUuid(userUuid)
            .newMatchSuccessAlarm(true)
            .likeMeAlarm(true)
            .newConversationAlarm(true)
            .talkAlarm(true)
            .build();
    }

    public void modify(final boolean newMatchSuccessAlarm, final boolean likeMeAlarm,
        final boolean newConversationAlarm, final boolean talkAlarm) {

        this.newMatchSuccessAlarm = newMatchSuccessAlarm;
        this.likeMeAlarm = likeMeAlarm;
        this.newConversationAlarm = newConversationAlarm;
        this.talkAlarm = talkAlarm;
    }
}
