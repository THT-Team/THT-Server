package com.tht.api.app.controller;

import com.tht.api.app.entity.user.User;
import com.tht.api.app.facade.chat.ChatFacade;
import com.tht.api.app.facade.chat.response.ChatResponse;
import com.tht.api.app.facade.chat.response.ChatRoomPreviewResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("chat/rooms")
    public ResponseEntity<List<ChatRoomPreviewResponse>> findMyChatRoom(@AuthenticationPrincipal final User user) {

        return ResponseEntity.ok(chatFacade.findMyRoom(user.getUserUuid()));
    }

}
