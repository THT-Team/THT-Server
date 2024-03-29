package com.tht.api.app.entity.chat;

import com.tht.api.app.entity.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@NoArgsConstructor
@Table(name = "chat_room_user")
public class ChatRoomUser extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @Column(name = "user_uuid")
    private String userUuid;

    @Column(name = "chat_room_idx")
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
