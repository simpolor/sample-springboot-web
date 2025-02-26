package io.simpolor.websocket.service;

import io.simpolor.websocket.model.ChatRoom;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatRoomService {

    private final Map<String, ChatRoom> chatRooms = new LinkedHashMap<>();

    // 채팅방 생성
    public ChatRoom createChatRoom(String name) {
        ChatRoom chatRoom = ChatRoom.create(name);
        chatRooms.put(chatRoom.getId(), chatRoom);
        return chatRoom;
    }

    // 채팅방 목록 조회
    public List<ChatRoom> findAllRooms() {
        return new ArrayList<>(chatRooms.values());
    }

    // 특정 채팅방 조회
    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }
}
