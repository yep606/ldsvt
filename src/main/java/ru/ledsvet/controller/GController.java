package ru.ledsvet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ledsvet.domain.User;
import ru.ledsvet.repo.UserRepository;

import java.util.List;
import java.util.Map;

@Controller
public class GController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model){
        Iterable<User> users = userRepository.findAll();
        model.put("users", users);
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String name,
                      @RequestParam String phoneNumber,
                      Map<String, Object> model) {
        User user = new User(name, phoneNumber);
        userRepository.save(user);

        Iterable<User> users = userRepository.findAll();
        model.put("users", users);

        return "redirect:main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String phoneNumber,
                         Map<String, Object> model){
        List<User> users = userRepository.findByPhoneNumber(phoneNumber);

        model.put("users", users);

        return "main";
    }

}
