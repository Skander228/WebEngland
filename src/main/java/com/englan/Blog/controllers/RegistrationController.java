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
    public String registration(Model model) {

        return "registration";
    }

    @PostMapping("/registration")
    public String usersAdd(@RequestParam String usern,
                           @RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String password,
                           @RequestParam String password_2,
                           @RequestParam String email,
                           @RequestParam String city, User user, Model model) {

        User userFromDb = userRepository.findByUsern(usern);
        User emailFromDb = userRepository.findByEmail(email);

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$";
        final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

        if (userFromDb != null) {
            model.addAttribute("error_name", "Такое имя уже занято");
            return "registration";
        } else if (usern.length() >= 20 || usern.length() < 3) {
            model.addAttribute("name_error", "Пользовательское имя должно быть не мение" +
                    " 3 символов и не более 20. Должно состоять из латинских символов и цифр.");
            return "registration";
        } else if (firstName.length() >= 20 || firstName.length() < 3) {
            model.addAttribute("firstName_error", "Имя должно быть не мение 3 символов и не более 20");
            return "registration";
        } else if (lastName.length() >= 20 || lastName.length() < 3)  {
            model.addAttribute("lastName_error", "Фамилия должна быть не мение 3 символов и не более 20");
            return "registration";
        } else if (city.length() > 20 || city.length() < 3) {
            model.addAttribute("city_error", "Город должен быть не мение 3 символов и не более 20");
            return "registration";
        } else if (emailFromDb != null) {
            model.addAttribute("error_email", "Такая email уже занят");
            return "registration";
        } else if (!email.matches(EMAIL_REGEX)) {
            model.addAttribute("email_error", "Неверно введен email");
            return "registration";
        } else if (!password.matches(PASSWORD_PATTERN)){
            model.addAttribute("password_error", "Должен быть из латинских символов. " +
                    "В пароле заглавная буква, строчная буква, цифра должна встречаться " +
                    "хотя бы один раз. Пароль не должен быть меньше 8 симолов и не более 20.");
            return "registration";
        } else if (!password.equals(password_2)) {
            model.addAttribute("password_err", "Пароли не совпадают");
            return "registration";
        }  else {
            user = new User(usern, firstName, lastName, password, email, city);
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            userRepository.save(user);
        }

        return "redirect:/login";
    }
}