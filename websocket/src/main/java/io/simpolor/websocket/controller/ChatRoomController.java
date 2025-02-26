package io.simpolor.websocket.controller;

import io.simpolor.websocket.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatRoomController {


    private final ChatRoomService chatRoomService;

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    public String getAllRooms(Model model) {
        model.addAttribute("rooms", chatRoomService.findAllRooms());
        return "chatRooms";
    }

    // 특정 채팅방 입장 페이지 반환
    @GetMapping("/{roomId}")
    public String chatRoom(@PathVariable String roomId, Model model) {
        model.addAttribute("roomId", roomId);
        return "chatRoom";
    }
}
