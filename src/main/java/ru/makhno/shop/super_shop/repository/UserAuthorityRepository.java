package ru.makhno.shop.super_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.makhno.shop.super_shop.entity.UserAuthority;

import java.util.List;

public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Integer> {
    List<UserAuthority> findByIdUser(Long idUser);
}
