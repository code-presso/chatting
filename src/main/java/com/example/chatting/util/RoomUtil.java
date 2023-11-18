package com.example.chatting.util;

import java.util.Random;

public class RoomUtil {

    /**
     * 채팅방 고유값 생성
     */
    public static String randomRoomId() {
        Random random = new Random();
        return String.valueOf(random.nextInt(1000, 10000));
    }
}
