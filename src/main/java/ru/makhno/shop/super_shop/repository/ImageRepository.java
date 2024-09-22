package ru.makhno.shop.super_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.makhno.shop.super_shop.entity.Image;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query("SELECT i FROM Image i WHERE i.productId = :productId ORDER BY i.id ASC LIMIT 1")
    Image findFirstImageByProductId(@Param("productId") Long productId);


    List<Image> findAllByProductId(Long productId);
    Optional<Image> findById(Long id);
}
