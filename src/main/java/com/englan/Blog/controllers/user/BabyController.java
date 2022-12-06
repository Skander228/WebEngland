package com.englan.Blog.controllers.user;

import com.englan.Blog.models.Baby;
import com.englan.Blog.models.User;
import com.englan.Blog.repo.BabyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BabyController {

    @Autowired
    private BabyRepository babyRepository;

    @GetMapping("/user/baby")
    public String mainBaby(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("name", user.getUsern());
        return "user/baby";
    }

    @PostMapping("user/baby")
    public String addBaby(@AuthenticationPrincipal User user,
                          @RequestParam String firstName,
                          @RequestParam String lastName, Model model) {

        model.addAttribute("name", user.getUsern());

        if (firstName.length() < 3 || firstName.length() > 20) {
            model.addAttribute("firstName_error", "Имя должно быть не мение 3 символов и не более 20");
            return "user/baby";
        } else if (lastName.length() < 3 || lastName.length() > 20) {
            model.addAttribute("lastName_error", "Фамилия должна быть не мение 3 символов и не более 20");
            return "user/baby";
        } else {
            Baby baby = new Baby(firstName, lastName, user);
            babyRepository.save(baby);
            return "user/children";
        }
    }

    @GetMapping("user/children")
    public String children(@AuthenticationPrincipal() User user, Baby baby, Model model) {
        List<Baby> wtf = babyRepository.getAllByUserId(user);
        model.addAttribute("baby", wtf);
        model.addAttribute("name", user.getUsern());
        return "user/children";
    }
}
