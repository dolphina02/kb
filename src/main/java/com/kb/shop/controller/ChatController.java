package com.kb.shop.controller;

import com.kb.shop.domain.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @GetMapping("/chat/{productId}")
    public String chat(@PathVariable Long productId, Model model, HttpSession session) {
        String sessionId = session.getId();
        model.addAttribute("productId", productId);
        model.addAttribute("sessionId", sessionId);
        logger.info("Session ID: " + sessionId);
        return "chat";
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/chat")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        logger.info("Received message: Sender={}, Content={}, Type={}", chatMessage.getSender(), chatMessage.getContent(), chatMessage.getType());
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/chat")
    public ChatMessage addUser(ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        logger.info("User added: Sender={}", chatMessage.getSender());
        return chatMessage;
    }
}
