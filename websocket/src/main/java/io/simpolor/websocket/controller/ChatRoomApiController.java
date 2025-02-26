package io.simpolor.websocket.controller;

import io.simpolor.websocket.model.ChatRoom;
import io.simpolor.websocket.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatRoomApiController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/rooms")
    public List<ChatRoom> getRoomsAsJson() {
        return chatRoomService.findAllRooms();
    }

    // 새로운 채팅방 생성
    @PostMapping("/room")
    public ChatRoom createRoom(@RequestBody ChatRoom chatRoom) {
        String name = chatRoom.getName();
        return chatRoomService.createChatRoom(name);
    }
}
