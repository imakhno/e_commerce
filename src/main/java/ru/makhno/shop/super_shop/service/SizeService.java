package ru.makhno.shop.super_shop.service;

import ru.makhno.shop.super_shop.entity.Color;
import ru.makhno.shop.super_shop.entity.Size;

import java.util.List;

public interface SizeService {
    List<Size> findSizesByProductIdAndCategoryId(Long productId, Long categoryId);
}
