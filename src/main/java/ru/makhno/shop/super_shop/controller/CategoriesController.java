package ru.makhno.shop.super_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.makhno.shop.super_shop.entity.MyUser;
import ru.makhno.shop.super_shop.service.CategoryService;
import ru.makhno.shop.super_shop.service.UserService;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class CategoriesController {

    private final CategoryService categoryService;
    private final UserService userService;

    @GetMapping()
    public String getCategories(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Проверяем, что пользователь аутентифицирован
        if (authentication != null && authentication.isAuthenticated()) {
            // Получаем имя пользователя
            String userName = authentication.getName();
            MyUser user = this.userService.findByUsername(userName);
            // Добавляем имя пользователя в модель
            model.addAttribute("user", user);

        }
        model.addAttribute(
                "categories",
                this.categoryService.findAllCategories());
        return "index";
    }
}
