package ru.makhno.shop.super_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.makhno.shop.super_shop.components.UserAuthCheck;
import ru.makhno.shop.super_shop.entity.*;
import ru.makhno.shop.super_shop.repository.CatalogRepository;
import ru.makhno.shop.super_shop.service.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("categories/{categoryId:\\d+}")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final ImageService imageService;
    private final UserService userService;
    private final CatalogService catalogService;
    private final UserAuthCheck userAuthCheck;



    @GetMapping()
    public String getCategories(@PathVariable("categoryId") Long categoryId, Model model) {
        List<Category> categories = this.categoryService.findAllCategories();
        List<Product> products = this.productService.findAllProductsByCategoryId(categoryId);
        List<Catalog> catalogList = this.catalogService.findAllByCategoryId(categoryId);
        Category category = this.categoryService.findCategoryById(categoryId).orElseThrow(
                () -> new NoSuchElementException("error.category.not_found")
        );

        Map<Long, Integer> productMinPriceMap = catalogList.stream()
                .collect(Collectors
                        .toMap(
                                Catalog::getProductId,
                                Catalog::getPrice,
                                Math::min
                        ));

        this.userAuthCheck.addAuthenticatedUserToModel(model);
        model.addAttribute("categories", categories);
        model.addAttribute("category", category);
        model.addAttribute("products", products);
        model.addAttribute("productMinPriceMap", productMinPriceMap);
        return "category";
    }

    @GetMapping(value = "/images/{productId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long productId) {
        Image image = this.imageService.findFirstImageByProductId(productId);
        if (image != null) {
            // Определяем MIME-тип по расширению файла
            String fileName = image.getFileName();
            String mimeType = getMimeType(fileName);

            // Проверяем, найден ли MIME-тип
            if (mimeType == null) {
                return ResponseEntity.badRequest().build();
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(mimeType))
                    .body(image.getImage());
        }
        return ResponseEntity.notFound().build();
    }

    // Метод для определения MIME-типа по расширению файла
    private String getMimeType(String fileName) {
        if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
            return MediaType.IMAGE_JPEG_VALUE;
        } else if (fileName.endsWith(".png")) {
            return MediaType.IMAGE_PNG_VALUE;
        } else if (fileName.endsWith(".gif")) {
            return MediaType.IMAGE_GIF_VALUE;
        } else {
            // Вернуть null или другой тип ошибки, если тип не поддерживается
            return null;
        }
    }


}
