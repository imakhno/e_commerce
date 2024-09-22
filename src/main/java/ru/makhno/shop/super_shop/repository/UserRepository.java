package ru.makhno.shop.super_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.makhno.shop.super_shop.entity.MyUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByUsername(String username);



    boolean existsByUsername(String username);

    // Метод для проверки существования пользователя по телефону
    boolean existsByPhone(String phone);
}
