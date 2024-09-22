package ru.makhno.shop.super_shop.service;

import ru.makhno.shop.super_shop.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAllCategories();
    Optional<Category> findCategoryById(Long categoryId);

}
