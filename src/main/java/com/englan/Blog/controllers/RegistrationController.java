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
public class RegistrationController {

    //Создаем поле для ссылки на интерфейс UsersRegistration, @Autowired - нужен для ссылки на репозиторий
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String blog(Model model) {
        //Iterable - это массив данных в котором будут содержаться все значения из определенной таблицы бд
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "registration";
    }

    @PostMapping("/registration")
    public String usersAdd(@RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String password,
                           @RequestParam String email,
                           @RequestParam String city, /*User user,*/ Model model) {

        User users = new User(firstName, lastName, password, email, city);

        //User userFromDb = userRepository.findByEmail(user.getEmail());
/*if (userFromDb != null) {
                model.addAttribute("massage", "Email exists!");
                return "registration";
            }*/

        users.setActive(true);
        users.setRoles(Collections.singleton(Role.USER));
        userRepository.save(users);

        return "registration";
    }
}