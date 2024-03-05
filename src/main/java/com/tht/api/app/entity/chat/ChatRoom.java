package com.tht.api.app.entity.chat;

import com.tht.api.app.entity.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
@Table(name = "chat_room")
public class ChatRoom extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @Column(name = "daily_falling_idx")
    private Long dailyFallingIdx;

    private ChatRoom(Long idx, Long dailyFallingIdx) {
        this.idx = idx;
        this.dailyFallingIdx = dailyFallingIdx;
    }

    public static ChatRoom create(final long dailyTopicIdx) {
        return new ChatRoom(null, dailyTopicIdx);
    }

}
