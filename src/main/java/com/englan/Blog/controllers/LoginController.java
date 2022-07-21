package com.englan.Blog.controllers;

import com.englan.Blog.models.Role;
import com.englan.Blog.models.User;
import com.englan.Blog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Collections;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String login(Model model) {

        return "login";
    }

   /* @PostMapping("/login")
    public String loginPost(@RequestParam String email,
                            @RequestParam String password,
                            User user, Model model) {
        return "redirect:/login";
    }*/
}