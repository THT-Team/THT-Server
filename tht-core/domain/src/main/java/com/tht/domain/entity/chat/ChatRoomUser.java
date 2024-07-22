package com.tht.domain.entity.chat;

import com.tht.domain.entity.Auditable;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@NoArgsConstructor
@Table
public class ChatRoomUser extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idx;

    @Column
    private String userUuid;

    @Column
    private Long chatRoomIdx;

    private ChatRoomUser(Long idx, String userUuid, Long chatRoomIdx) {
        this.idx = idx;
        this.userUuid = userUuid;
        this.chatRoomIdx = chatRoomIdx;
    }

    public static ChatRoomUser create(final long chatRoomIdx, final String userUuid) {
        return new ChatRoomUser(null, userUuid, chatRoomIdx);
    }
}
