package ru.makhno.shop.super_shop.components;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import ru.makhno.shop.super_shop.entity.MyUser;
import ru.makhno.shop.super_shop.service.UserService;

@Component
@RequiredArgsConstructor
public class UserAuthCheck {
    private final UserService userService;


    public void addAuthenticatedUserToModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            MyUser user = this.userService.findByUsername(username);
            model.addAttribute("user", user);
        }
    }

}
