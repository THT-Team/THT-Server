package fixture.chat;

import com.tht.domain.entity.chat.mapper.ChatRoomMapper;
import com.tht.enums.EntityState;
import java.time.Instant;

public class ChatRoomMapperFixture {

    private static final long chatRoomIdx = 1;
    private static final String talkSubject = "마음";
    private static final String talkIssue = "너의 마음은 어떠니?";
    private static final Instant startDate = Instant.now();
    private static final EntityState isOutPartner = EntityState.ACTIVE;
    private static final EntityState isWithDrawPartner = EntityState.ACTIVE;

    public static ChatRoomMapper make() {
        return new ChatRoomMapper(chatRoomIdx, talkSubject, talkIssue, startDate, isOutPartner,
            isWithDrawPartner);
    }

}
