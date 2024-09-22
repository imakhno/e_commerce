package ru.makhno.shop.super_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.makhno.shop.super_shop.service.CategoryService;
import ru.makhno.shop.super_shop.service.UserService;

@Controller
@RequestMapping("/reg")
@RequiredArgsConstructor
public class RegisterController {
    private final CategoryService categoryService;

    @GetMapping
    public String getRegisterPage(Model model, Authentication authentication) {
        // Проверяем, если пользователь уже аутентифицирован
        if (authentication != null && authentication.isAuthenticated()) {
            // Если аутентифицирован, перенаправляем на страницу аккаунта
            return "redirect:/account";
        }

        // Если не аутентифицирован, добавляем категории в модель и отображаем страницу регистрации
        model.addAttribute("categories", this.categoryService.findAllCategories());
        return "reg";
    }
}

