package ru.makhno.shop.super_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.makhno.shop.super_shop.entity.Color;

import java.util.List;

public interface ColorRepository extends JpaRepository<Color, Long> {
    @Query("SELECT DISTINCT color " +
            "FROM Color color " +
            "INNER JOIN Catalog catalog ON color.id = catalog.colorId " +
            "WHERE catalog.productId = :productId " +
            "AND catalog.categoryId = :categoryId")
    List<Color> findColorsByProductIdAndCategoryId(@Param("productId") Long productId,
                                                   @Param("categoryId") Long categoryId);
}
