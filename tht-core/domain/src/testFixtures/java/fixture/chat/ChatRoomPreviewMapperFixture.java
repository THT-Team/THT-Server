package fixture.chat;

import com.tht.domain.entity.chat.mapper.ChatRoomPreviewMapper;
import com.tht.enums.EntityState;
import java.time.Instant;

public class ChatRoomPreviewMapperFixture {

    private static final Long chatRoomIdx = 1L;
    private static final String partnerUuid = "uuid";
    private static final String partnerName = "상대방 이름";
    private static final String partnerProfileUrl = "profile_url";
    private static final Instant chatRoomCreatedAt = Instant.now();
    private static final EntityState userState = EntityState.ACTIVE;
    private static final EntityState entityState = EntityState.ACTIVE;

    public static ChatRoomPreviewMapper make(final Long roomIdx) {

        return new ChatRoomPreviewMapper(roomIdx, partnerUuid, partnerName, partnerProfileUrl, chatRoomCreatedAt, entityState, userState);
    }

    public static ChatRoomPreviewMapper make() {

        return new ChatRoomPreviewMapper(chatRoomIdx, partnerUuid, partnerName, partnerProfileUrl, chatRoomCreatedAt, entityState, userState);
    }
}
