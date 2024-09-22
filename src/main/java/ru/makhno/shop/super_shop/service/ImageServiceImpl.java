package ru.makhno.shop.super_shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.makhno.shop.super_shop.entity.Image;
import ru.makhno.shop.super_shop.repository.ImageRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Override
    public Image findFirstImageByProductId(Long productId) {
        return this.imageRepository.findFirstImageByProductId(productId);
    }

    @Override
    public List<Image> findAllByProductId(Long productId) {
        return this.imageRepository.findAllByProductId(productId);
    }

    @Override
    public Optional<Image> findById(Long id) {
        return imageRepository.findById(id);
    }


}
