package ru.makhno.shop.super_shop.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.makhno.shop.super_shop.entity.MyUser;
import ru.makhno.shop.super_shop.entity.UserAuthority;
import ru.makhno.shop.super_shop.repository.UserAuthorityRepository;
import ru.makhno.shop.super_shop.repository.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserAuthorityRepository userAuthorityRepository;

    @Transactional
    public void registerUser(
            String name,
            String surname,
            String username,
            String password,
            String phone
    ) throws IOException {

        // Проверка уникальности username
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Такой логин уже существует");
        }

        // Проверка уникальности телефона
        if (userRepository.existsByPhone(phone)) {
            throw new IllegalArgumentException("Такой телефон уже существует");
        }

        Path imagePath =
                Paths.get("D:/Java/super-shop/src/main/resources/static/images/icons/profile-picture.png");
        byte[] defaultProfilePicture = Files.readAllBytes(imagePath);

        String encodedPassword = passwordEncoder.encode(password);

        MyUser user = new MyUser(name, surname, encodedPassword, username, phone);
        user.setProfilePicture(defaultProfilePicture);
        userRepository.save(user);

        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setIdUser(Math.toIntExact(user.getId()));
        userAuthority.setIdAuthority(1);
        userAuthorityRepository.save(userAuthority);
    }

    public MyUser findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    public void updateUserInfo(MyUser user) {
        userRepository.save(user);
    }
}
