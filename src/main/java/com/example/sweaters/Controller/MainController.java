package com.example.sweaters.Controller;

import com.example.sweaters.Entity.MessageEntity;
import com.example.sweaters.Entity.UserEntity;
import com.example.sweaters.Repository.MessageRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    private final MessageRepository messageRepository;

    public MainController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter , Model model) {
        Iterable<MessageEntity> messages = messageRepository.findAll();

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepository.findByTag(filter);
        } else {
            messages = messageRepository.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal UserEntity userEntity,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model
    ) {
        MessageEntity messageEntity = new MessageEntity(text, tag, userEntity);

        messageRepository.save(messageEntity);

        Iterable<MessageEntity> messages = messageRepository.findAll();
        model.put("messages", messages);

        return "main";
    }

}
