package com.tht.infra.chat;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDateTime;

@Getter
@Document(collection = "chat_history")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ChatHistory {

    @Id
    @Field(value = "_id", targetType = FieldType.OBJECT_ID)
    private String id;

    @Field("room_idx")
    private Long roomIdx;

    @Field("sender_name")
    private String senderName;

    @Field("sender_uuid")
    private String senderUuid;

    @Field("msg")
    private String msg;

    @Field("img_url")
    private String imgUrl;

    @Field("created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Field("updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder(access = AccessLevel.PRIVATE)
    private ChatHistory(String id, Long roomIdx, String senderName, String senderUuid, String msg,
        String imgUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {

        this.id = id;
        this.roomIdx = roomIdx;
        this.senderName = senderName;
        this.senderUuid = senderUuid;
        this.msg = msg;
        this.imgUrl = imgUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ChatHistory of(long roomIdx, String senderName, String senderUuid, String msg,
        String imgUrl) {

        return ChatHistory.builder()
            .roomIdx(roomIdx)
            .senderName(senderName)
            .senderUuid(senderUuid)
            .msg(msg)
            .imgUrl(imgUrl)
            .build();
    }

}
