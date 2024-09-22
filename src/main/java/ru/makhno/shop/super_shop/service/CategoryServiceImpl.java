package ru.makhno.shop.super_shop.service;

import org.springframework.stereotype.Service;
import ru.makhno.shop.super_shop.entity.Category;
import ru.makhno.shop.super_shop.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findCategoryById(Long categoryId) {
        return this.categoryRepository.findCategoryById(categoryId);
    }


}
