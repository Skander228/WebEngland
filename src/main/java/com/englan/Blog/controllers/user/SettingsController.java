package com.englan.Blog.controllers.user;

import com.englan.Blog.models.User;
import com.englan.Blog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;


@Controller
public class SettingsController {

    @Autowired
    private UserRepository usersRepository;

    @GetMapping("/user/settings")
    public String settingsId(@AuthenticationPrincipal User user, Model model) {
        Optional<User> users = usersRepository.findById(user.getId());
        ArrayList<User> res = new ArrayList<>();
        users.ifPresent(res::add);
        model.addAttribute("users", res);
        model.addAttribute("name", user.getUsern());
        return "user/settings";
    }

    @PostMapping("/user/settings")
    public String settingsUpdate(@AuthenticationPrincipal User user,
                                 @RequestParam String usern,
                                 @RequestParam String lastName,
                                 @RequestParam String firstName,
                                 @RequestParam String email,
                                 @RequestParam String password,
                                 @RequestParam String password_2,
                                 @RequestParam String city, Model model) {

        Optional<User> users = usersRepository.findById(user.getId());
        ArrayList<User> res = new ArrayList<>();
        users.ifPresent(res::add);
        model.addAttribute("users", res);
        model.addAttribute("name", user.getUsern());

        User userFromDb = usersRepository.findByUsern(usern);
        User emailFromDb = usersRepository.findByEmail(email);

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$";
        final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

        if (userFromDb != null && !usern.equals(user.getUsern())) {
            model.addAttribute("error_name", "Такое имя уже занято");
            return "user/settings";
        } else if (usern.length() >= 20 || usern.length() < 3) {
            model.addAttribute("name_error", "Пользовательское имя должно быть не мение" +
                    " 3 символов и не более 20. Должно состоять из латинских символов и цифр.");
            return "user/settings";
        } else if (firstName.length() >= 20 || firstName.length() < 3) {
            model.addAttribute("firstName_error", "Имя должно быть не мение 3 символов и не более 20");
            return "user/settings";
        } else if (lastName.length() >= 20 || lastName.length() < 3)  {
            model.addAttribute("lastName_error", "Фамилия должна быть не мение 3 символов и не более 20");
            return "user/settings";
        } else if (city.length() > 20 || city.length() < 3) {
            model.addAttribute("city_error", "Город должен быть не мение 3 символов и не более 20");
            return "user/settings";
        } else if (emailFromDb != null && !email.equals(user.getEmail())) {
            model.addAttribute("error_email", "Такая email уже занят");
            return "user/settings";
        } else if (!email.matches(EMAIL_REGEX)) {
            model.addAttribute("email_error", "Неверно введен email");
            return "user/settings";
        } else if (!password.matches(PASSWORD_PATTERN)){
            model.addAttribute("password_error", "Должен быть из латинских символов. " +
                    "В пароле заглавная буква, строчная буква, цифра должна встречаться " +
                    "хотя бы один раз. Пароль не должен быть меньше 8 симолов и не более 20.");
            return "user/settings";
        } else if (!password.equals(password_2)) {
            model.addAttribute("password_err", "Пароли не совпадают");
            return "redirect:/user/settings";
        }  else {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(password);
            user.setCity(city);
            usersRepository.save(user);
            return "redirect:/user/im";
        }
    }
}