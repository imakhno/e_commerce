package ru.makhno.shop.super_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.makhno.shop.super_shop.entity.*;
import ru.makhno.shop.super_shop.repository.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAllProductsByCategoryId(Long categoryId) {
        return productRepository.findProductByCategoryId(categoryId);
    }

    @Override
    public Optional<Product> findProductById(Long productId) {
        return this.productRepository.findProductById(productId);
    }

}
