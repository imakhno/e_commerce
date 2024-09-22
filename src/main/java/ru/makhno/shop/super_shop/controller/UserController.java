package ru.makhno.shop.super_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.makhno.shop.super_shop.service.UserService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public String registerUser(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("phone") String phone,
            Model model
    ) throws IOException {
        try {
            userService.registerUser(name, surname, username, password, phone);
            return "redirect:/auth";  // Перенаправление на страницу аутентификации при успешной регистрации
        } catch (IllegalArgumentException e) {
            // Если произошла ошибка (например, логин или телефон уже существуют), передаем сообщение в модель
            model.addAttribute("errorMessage", e.getMessage());
            return "reg";  // Возврат на страницу регистрации с сообщением об ошибке
        }
    }
}
