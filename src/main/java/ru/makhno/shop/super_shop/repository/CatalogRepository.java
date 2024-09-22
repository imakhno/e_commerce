package ru.makhno.shop.super_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.makhno.shop.super_shop.entity.Catalog;

import java.util.List;
import java.util.Optional;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {

    Optional<Catalog> findByProductId(Long productId);

    @Query("SELECT catalog FROM Catalog catalog " +
            "WHERE catalog.price = " +
            "(SELECT MIN(catalog2.price) FROM Catalog catalog2 " +
            "WHERE catalog2.productId = catalog.productId)")
    List<Catalog> findAllByCategoryId(Long categoryId);


    List<Catalog> findAllByCategoryIdAndProductId(Long categoryId, Long productId);

   @Query("SELECT catalog.price, catalog.stock FROM Catalog catalog " +
           "WHERE catalog.categoryId = :categoryId " +
           "AND catalog.productId = :productId " +
           "AND catalog.colorId = :colorId " +
           "AND catalog.sizeId = :sizeId")
    Long findPriceByCategoryIdAndProductIdAndColorIdAndSizeId(
            @Param("categoryId") Long categoryId,
            @Param("productId") Long productId,
            @Param("colorId") Long colorId,
            @Param("sizeId") Long sizeId
    );

   @Query("SELECT catalog.stock FROM Catalog catalog " +
           "WHERE catalog.categoryId = :categoryId " +
           "AND catalog.productId = :productId " +
           "AND catalog.colorId = :colorId " +
           "AND catalog.sizeId = :sizeId")
    Long findStockByCategoryIdAndProductIdAndColorIdAndSizeId(
            @Param("categoryId") Long categoryId,
            @Param("productId") Long productId,
            @Param("colorId") Long colorId,
            @Param("sizeId") Long sizeId
    );


}
