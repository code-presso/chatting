package com.example.chatting.controller;

import com.example.chatting.dto.ChatDTO;
import com.example.chatting.dto.ChatRoom;
import com.example.chatting.dto.RoomRequest;
import com.example.chatting.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRepository chatRepository;

    // 해당 채팅방 채팅내역 표시
    @PostMapping("/chat/chatlist")
    public ModelAndView goChatRoom(@RequestBody RoomRequest roomRequest, ModelAndView mav){
        //1. 조회
        List<ChatDTO> list = chatRepository.findRoomChatting(roomRequest.getRoomId());

        //return
        mav.addObject("list", list);
        mav.setViewName("jsonView");
        return mav;
    }

    // 채팅방 생성
    @PostMapping("/chat/createroom")
    public ModelAndView createRoom(@RequestBody RoomRequest roomRequest, ModelAndView mav) {

        //2. 채팅방 생성
        ChatRoom room = chatRepository.createChatRoom(roomRequest.getRoomName());
        log.info("CREATE Chat Room {}", room);

        //return
        mav.addObject("roomId", room.getRoomId());
        mav.setViewName("jsonView");
        return mav;
    }

    // 채팅에 참여한 유저 리스트 반환
    @PostMapping("/chat/userlist")
    public ModelAndView userList(@RequestBody RoomRequest roomRequest, ModelAndView mav) {
        //1. 조회
        ArrayList<String> userList = chatRepository.getUserList(roomRequest.getRoomId());

        //return
        mav.addObject("userList", userList);
        mav.setViewName("jsonView");
        return mav;
    }
}
