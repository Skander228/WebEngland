package com.englan.Blog.controllers;

import com.englan.Blog.models.User;
import com.englan.Blog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("name", "Main");
        model.addAttribute("head", "Главная страница");
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users",users);

        return "main";
    }

}