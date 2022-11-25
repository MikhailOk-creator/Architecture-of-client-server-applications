package ru.rtu_mirea.practice_5;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MessageService {
    private final MessageRepo messageRepo;

    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public void addMessage(Message message) {
        messageRepo.save(message);
    }

    public Message getMessage(int id) {
        return messageRepo.findById(id).orElse(null);
    }

    public List<Message> getAllMessages() {
        return messageRepo.findAll();
    }

    public void deleteMessage(int id) {
        messageRepo.deleteById(id);
    }
}
