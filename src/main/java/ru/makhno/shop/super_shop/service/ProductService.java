package ru.makhno.shop.super_shop.service;

import ru.makhno.shop.super_shop.entity.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProductsByCategoryId(Long categoryId);
    Optional<Product> findProductById(Long productId);
}
