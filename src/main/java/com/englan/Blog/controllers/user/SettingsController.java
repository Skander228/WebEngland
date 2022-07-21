package com.englan.Blog.controllers.user;

import com.englan.Blog.models.User;
import com.englan.Blog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.Optional;


@Controller
public class SettingsController {

    @Autowired
    private UserRepository usersRepository;

    @GetMapping("/user/settings")
    public String settingsId(Model model) {
        long id = 14;
        Optional<User> users = usersRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        users.ifPresent(res::add);
        model.addAttribute("users", res);
        return "user/settings";
    }

    @PostMapping("/user/settings")
    public String settingsUpdate(@RequestParam String email,
                                 @RequestParam String password,
                                 @RequestParam String city,
                                 @RequestParam String lastName,
                                 @RequestParam String firstName, Model model) {
        long id = 14;
        User user = usersRepository.findById(id).orElseThrow();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setCity(city);
        usersRepository.save(user);
        return "redirect:/main";
    }
}