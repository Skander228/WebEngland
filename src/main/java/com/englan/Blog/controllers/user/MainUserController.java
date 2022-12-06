package com.englan.Blog.controllers.user;

import com.englan.Blog.models.User;
import com.englan.Blog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainUserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/main")
    public String mainUserName(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("name", user.getUsern());
        return "user/main";
    }

    @GetMapping("/user/im")
    public String userIm(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("userName", user.getUsern());
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("city", user.getCity());
        model.addAttribute("name", user.getUsern());
        return "user/im";
    }

}
