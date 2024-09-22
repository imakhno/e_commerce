package ru.makhno.shop.super_shop.service;

import org.springframework.data.repository.query.Param;
import ru.makhno.shop.super_shop.entity.Catalog;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CatalogService {
    Optional<Catalog> findByProductId(Long productId);

    List<Catalog> findAllByCategoryId(Long categoryId);

    List<Catalog> findAllByCategoryIdAndProductId(Long categoryId, Long productId);

    Long findPriceByCategoryIdAndProductIdAndColorIdAndSizeId(
            Long categoryId,
            Long productId,
            Long colorId,
            Long sizeId
    );
    Long findStockByCategoryIdAndProductIdAndColorIdAndSizeId(
            Long categoryId,
            Long productId,
            Long colorId,
            Long sizeId
    );


}
