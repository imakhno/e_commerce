package ru.makhno.shop.super_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.makhno.shop.super_shop.entity.Image;
import ru.makhno.shop.super_shop.service.ImageService;

import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/images/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable Long id) {
        Image image = this.imageService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("error.image.not_found"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(image.getImageType()));
        headers.setContentDispositionFormData("attachment", image.getFileName());

        return new ResponseEntity<>(image.getImage(), headers, HttpStatus.OK);
    }
}
