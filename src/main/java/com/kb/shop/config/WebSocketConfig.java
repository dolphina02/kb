package com.kb.shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker  // WebSocket 메시지 브로커를 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 메시지 브로커를 구성

        config.enableSimpleBroker("/topic");  // 메시지를 구독하는 클라이언트에게 브로드캐스트할 때 사용
        config.setApplicationDestinationPrefixes("/app");  // 애플리케이션 메시지의 목적지 접두사를 설정
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // STOMP 엔드포인트를 등록하여 SockJS 폴백 옵션을 활성화
        registry.addEndpoint("/ws").withSockJS();  // WebSocket 엔드포인트를 "/ws"로 설정하고 SockJS를 사용
    }
}
