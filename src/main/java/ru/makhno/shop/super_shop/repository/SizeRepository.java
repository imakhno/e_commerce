package ru.makhno.shop.super_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.makhno.shop.super_shop.entity.Size;

import java.util.List;

public interface SizeRepository extends JpaRepository<Size, Long> {
    @Query("SELECT DISTINCT s " +
            "FROM Size s " +
            "INNER JOIN Catalog catalog ON s.id = catalog.sizeId " +
            "WHERE catalog.productId = :productId " +
            "AND catalog.categoryId = :categoryId")
    List<Size> findSizeByProductIdAndCategoryId(
            @Param("productId") Long productId,
            @Param("categoryId") Long categoryId);


}
