package ru.makhno.shop.super_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.makhno.shop.super_shop.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
