package ru.makhno.shop.super_shop.service;

import org.springframework.data.repository.query.Param;
import ru.makhno.shop.super_shop.entity.Color;

import java.util.List;
import java.util.Set;

public interface ColorService {
    List<Color> findColorsByProductIdAndCategoryId(Long productId, Long categoryId);
}
