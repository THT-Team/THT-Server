package com.tht.api.app.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@ToString
@Table
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class UserAlarmAgreement {

    @Id
    @Column(name = "idx")
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
}
