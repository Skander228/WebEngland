package com.englan.Blog.controllers.admin;

import com.englan.Blog.models.User;
import com.englan.Blog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin/users")
    public String users(Model model) {
        Iterable<User> users = userRepository.findAllByOrderByIdDesc();
        model.addAttribute("users", users);
        return "admin/users";
    }

    @PostMapping("/admin/users")
    public String usersDelete(@RequestParam long delId, Model model) {
        long l = 11;
        User user = userRepository.findById(l).orElseThrow();
        userRepository.delete(user);
        return "redirect:/main";
    }
}
