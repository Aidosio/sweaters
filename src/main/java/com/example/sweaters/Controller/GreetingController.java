package com.example.sweaters.Controller;

import com.example.sweaters.Entity.MessageEntity;
import com.example.sweaters.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {

    private final MessageRepository messageRepository;

    public GreetingController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<MessageEntity> messages = messageRepository.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        MessageEntity messageEntity = new MessageEntity(text, tag);

        messageRepository.save(messageEntity);

        Iterable<MessageEntity> messages = messageRepository.findAll();
        model.put("messages", messages);

        return "main";
    }
}
