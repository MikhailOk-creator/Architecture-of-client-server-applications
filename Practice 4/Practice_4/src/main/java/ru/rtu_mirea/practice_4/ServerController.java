package ru.rtu_mirea.practice_4;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

public class ServerController {
    @MessageMapping("/chat")
    @SendTo("/topic/")
    public Message send(Message message) {
        return message;
    }

}
