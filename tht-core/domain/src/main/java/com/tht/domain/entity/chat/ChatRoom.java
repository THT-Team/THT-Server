package com.tht.domain.entity.chat;

import com.tht.domain.entity.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
@Table
public class ChatRoom extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idx;

    @Column
    private Long dailyFallingIdx;

    private ChatRoom(Long idx, Long dailyFallingIdx) {
        this.idx = idx;
        this.dailyFallingIdx = dailyFallingIdx;
    }

    public static ChatRoom create(final long dailyTopicIdx) {
        return new ChatRoom(null, dailyTopicIdx);
    }

}
