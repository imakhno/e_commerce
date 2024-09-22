package ru.makhno.shop.super_shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.makhno.shop.super_shop.entity.Color;
import ru.makhno.shop.super_shop.repository.ColorRepository;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;

    @Override
    public List<Color> findColorsByProductIdAndCategoryId(Long productId, Long categoryId) {
        return colorRepository.findColorsByProductIdAndCategoryId(productId, categoryId);
    }
}
