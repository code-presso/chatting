package com.example.chatting.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoomRequest {
    private String roomId; //방 고유키
    private String roomName; //방이름
}
