package com.example.chatting.dto;

import lombok.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

// Stomp 를 통해 pub/sub 를 사용하면 구독자 관리가 알아서 된다!!
// 따라서 따로 세션 관리를 하는 코드를 작성할 필도 없고,
// 메시지를 다른 세션의 클라이언트에게 발송하는 것도 구현 필요가 없다!
@Setter
@Getter
@ToString
@NoArgsConstructor
public class ChatRoom implements Serializable {
    private String roomId; // 채팅방 아이디
    private String roomName; // 채팅방 이름
    private long userCount; // 채팅방 인원수
    private HashMap<String, String> userlist; //userList
    private List<ChatDTO> chatDTOList; //채팅 리스트

    @Builder
    public ChatRoom(String roomId, String roomName, long userCount, HashMap<String, String> userlist, List<ChatDTO> chatDTOList) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.userCount = userCount;
        this.userlist = userlist;
        this.chatDTOList = chatDTOList;
    }
}
