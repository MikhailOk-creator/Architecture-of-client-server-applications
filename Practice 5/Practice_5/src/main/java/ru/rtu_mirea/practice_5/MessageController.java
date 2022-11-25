package ru.rtu_mirea.practice_5;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/add")
    public void addMessage(@RequestBody Message message) {
        messageService.addMessage(message);
    }

    @GetMapping("")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public Message getMessage(@PathVariable int id) {
        return messageService.getMessage(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMessage(@PathVariable int id) {
        messageService.deleteMessage(id);
    }
}
