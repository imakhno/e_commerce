package ru.makhno.shop.super_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.makhno.shop.super_shop.entity.Catalog;
import ru.makhno.shop.super_shop.repository.CatalogRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {
    private final CatalogRepository catalogRepository;

    @Override
    public Optional<Catalog> findByProductId(Long productId) {
        return this.catalogRepository.
                findByProductId(productId);
    }

    @Override
    public List<Catalog> findAllByCategoryId(Long categoryId) {
        return catalogRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public List<Catalog> findAllByCategoryIdAndProductId(Long categoryId, Long productId) {
        return this.catalogRepository.findAllByCategoryIdAndProductId(categoryId, productId);
    }

    @Override
    public Long findPriceByCategoryIdAndProductIdAndColorIdAndSizeId(Long categoryId, Long productId, Long colorId, Long sizeId) {
        return this.catalogRepository.findPriceByCategoryIdAndProductIdAndColorIdAndSizeId(
                categoryId,
                productId,
                colorId,
                sizeId
        );

    }

    @Override
    public Long findStockByCategoryIdAndProductIdAndColorIdAndSizeId(Long categoryId, Long productId, Long colorId, Long sizeId) {
        return this.catalogRepository.findStockByCategoryIdAndProductIdAndColorIdAndSizeId(
                categoryId,
                productId,
                colorId,
                sizeId
        );
    }


}
