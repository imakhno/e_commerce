package ru.makhno.shop.super_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.makhno.shop.super_shop.components.UserAuthCheck;
import ru.makhno.shop.super_shop.entity.MyUser;
import ru.makhno.shop.super_shop.repository.UserRepository;
import ru.makhno.shop.super_shop.service.CategoryService;
import ru.makhno.shop.super_shop.service.UserService;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final CategoryService categoryService;
    private final UserAuthCheck userAuthCheck;

    @GetMapping()
    public String accountPage(Model model) {
        this.userAuthCheck.addAuthenticatedUserToModel(model);
        model.addAttribute(
                "categories",
                this.categoryService.findAllCategories());
        return "account";
    }
}
