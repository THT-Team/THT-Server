package com.tht.thtapis.ui;

import java.util.List;

import com.tht.domain.entity.user.User;
import com.tht.thtapis.facade.chat.ChatFacade;
import com.tht.thtapis.facade.chat.response.ChatResponse;
import com.tht.thtapis.facade.chat.response.ChatRoomPreviewResponse;
import com.tht.thtapis.facade.chat.response.ChatRoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatFacade chatFacade;

    @GetMapping("/chat/history")
    public ResponseEntity<List<ChatResponse>> readChatHistory(
        @RequestParam(name = "roomNo") final long chatRoomId,
        @RequestParam(name = "chatIdx", required = false) final String chatIdx,
        @RequestParam(name = "size") final int size
    ) {

        return ResponseEntity.ok(chatFacade.readHistory(chatRoomId, chatIdx, size));
    }

    @GetMapping("/chat/rooms")
    public ResponseEntity<List<ChatRoomPreviewResponse>> findMyChatRoom(
        @AuthenticationPrincipal final User user) {

        return ResponseEntity.ok(chatFacade.findMyRoomList(user.getUserUuid()));
    }

    @PostMapping("/chat/out/room/{chat-room-idx}")
    public ResponseEntity<Object> outChat(
        @PathVariable(name = "chat-room-idx") final long chatRoomIdx,
        @AuthenticationPrincipal final User user
    ) {

        chatFacade.outChatting(chatRoomIdx, user.getUserUuid());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/chat/room/{chat-room-idx}")
    public ResponseEntity<ChatRoomResponse> findDetailChatRoom(
        @PathVariable(name = "chat-room-idx") final long chatRoomIdx,
        @AuthenticationPrincipal final User user) {

        return ResponseEntity.ok(chatFacade.findMyRoomDetail(chatRoomIdx, user.getUserUuid()));
    }
}
