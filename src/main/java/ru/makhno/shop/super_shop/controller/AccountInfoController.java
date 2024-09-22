package ru.makhno.shop.super_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.makhno.shop.super_shop.components.UserAuthCheck;
import ru.makhno.shop.super_shop.entity.MyUser;
import ru.makhno.shop.super_shop.service.CategoryService;
import ru.makhno.shop.super_shop.service.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/updateAccountInfo")
@RequiredArgsConstructor
public class AccountInfoController {
    private final UserService userService;
    private final CategoryService categoryService;
    private final UserAuthCheck userAuthCheck;

    @PostMapping
    public String updateUserInfo(
            @RequestParam("username") String username,
            @RequestParam("city") String city,
            @RequestParam("street") String street,
            @RequestParam("house") String house,
            @RequestParam("apartment") String apartment,
            Model model
    ) {


        MyUser user = this.userService.findByUsername(username);

        user.setCity(city);
        user.setStreet(street);
        user.setHouse(house);
        user.setApartment(apartment);

        this.userService.updateUserInfo(user);

        this.userAuthCheck.addAuthenticatedUserToModel(model);
        model.addAttribute("user", user);
        model.addAttribute(
                "categories",
                this.categoryService.findAllCategories());
        return "account";
    }
}
