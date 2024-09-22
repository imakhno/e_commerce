package ru.makhno.shop.super_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.makhno.shop.super_shop.entity.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductByCategoryId(Long categoryId);

    Optional<Product> findProductById(Long productId);
}
