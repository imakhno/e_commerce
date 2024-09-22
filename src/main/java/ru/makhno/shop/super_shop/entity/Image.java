package ru.makhno.shop.super_shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_id")
    private Long productId;
    private byte[] image;
    @Column(name = "image_type")
    private String imageType;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "file_size")
    private String fileSize;

}
