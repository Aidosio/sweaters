package com.example.sweaters.Controller;

import com.example.sweaters.Entity.Role;
import com.example.sweaters.Entity.UserEntity;
import com.example.sweaters.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(UserEntity userEntity, Map<String, Object> model) {
        UserEntity userFromDb = userRepository.findByUsername(userEntity.getUsername());
        if (userFromDb != null) {
            model.put("Message", "User exists");
            return "registration";
        }

        userEntity.setActive(true);
        userEntity.setRoles(Collections.singleton(Role.USER));
        userRepository.save(userEntity);
        return "redirect:/login";
    }
}
