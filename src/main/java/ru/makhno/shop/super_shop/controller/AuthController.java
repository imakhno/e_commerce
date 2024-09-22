package ru.makhno.shop.super_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.makhno.shop.super_shop.components.UserAuthCheck;
import ru.makhno.shop.super_shop.entity.MyUser;
import ru.makhno.shop.super_shop.service.CategoryService;
import ru.makhno.shop.super_shop.service.UserService;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final CategoryService categoryService;

    @GetMapping("/auth")
    public String loginPage(Model model, Authentication authentication) {
        // Проверяем, если пользователь уже аутентифицирован
        if (authentication != null && authentication.isAuthenticated()) {
            // Если аутентифицирован, перенаправляем на страницу аккаунта
            return "redirect:/account";
        }

        // Если не аутентифицирован, добавляем категории в модель и отображаем страницу логина
        model.addAttribute("categories", this.categoryService.findAllCategories());
        return "auth";
    }
}
