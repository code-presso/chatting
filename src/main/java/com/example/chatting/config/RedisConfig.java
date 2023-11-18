package com.example.chatting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    //redisTemplate 설정
    //redis서버에는 byte코드만이 저장됨.
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory); //연결
        redisTemplate.setKeySerializer(new StringRedisSerializer()); //직렬화
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(String.class)); //json포맷 형식으로 메세지 교환
        return redisTemplate;
    }
}
