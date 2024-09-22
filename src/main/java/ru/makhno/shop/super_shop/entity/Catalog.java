package ru.makhno.shop.super_shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "catalog")
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "size_id")
    private Long sizeId;
    @Column(name = "color_id")
    private Long colorId;
    private Integer price;
    private Integer stock;
}
