package com.example.chatting.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    //메세지 브로커가 지원하는 웹소켓 메시지 처리 활성화
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //메세지 받을 경로
        // 메세지 받는곳
        registry.enableSimpleBroker("/sub");
        // 메세지 보내는 곳
        registry.setApplicationDestinationPrefixes("/pub");
    }

    //웹소켓 연결을 위한 엔드포인트 등록
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //stomp 접속 주소
        registry.addEndpoint("/ws-stomp");
        //.withSockJS(); SocketJS 를 연결한다는 설정
    }


}
