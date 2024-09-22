package ru.makhno.shop.super_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.makhno.shop.super_shop.entity.Size;
import ru.makhno.shop.super_shop.repository.SizeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {
    private final SizeRepository sizeRepository;
    @Override
    public List<Size> findSizesByProductIdAndCategoryId(Long productId, Long categoryId) {
        return sizeRepository.findSizeByProductIdAndCategoryId(productId,categoryId);
    }
}
