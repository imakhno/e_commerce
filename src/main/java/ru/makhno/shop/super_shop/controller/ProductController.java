package ru.makhno.shop.super_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.makhno.shop.super_shop.entity.*;
import ru.makhno.shop.super_shop.service.*;

import java.util.*;

@Controller
@RequestMapping("categories/{categoryId:\\d+}/{productId:\\d+}")
@RequiredArgsConstructor
public class ProductController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final CatalogService catalogService;
    private final ColorService colorService;
    private final SizeService sizeService;
    private final ImageService imageService;

    @GetMapping()
    public String getProduct(
            @PathVariable("productId") Long productId,
            @PathVariable("categoryId") Long categoryId,
            Model model) {
        List<Category> categories = this.categoryService.findAllCategories();
        Category category = this.categoryService.findCategoryById(categoryId)
                .orElseThrow(() -> new NoSuchElementException("error.category.not_found"));
        Product product = this.productService.findProductById(productId)
                .orElseThrow(() -> new NoSuchElementException("error.product.not_found"));
        List<Catalog> catalogList = this.catalogService.findAllByCategoryIdAndProductId(categoryId, productId);

        List<Color> colorList =
                this.colorService.findColorsByProductIdAndCategoryId(
                        productId,
                        categoryId);

        List<Size> sizeList = this.
                sizeService.findSizesByProductIdAndCategoryId(productId, categoryId);

        List<Image> images = imageService.findAllByProductId(productId);
        List<Long> imageIds = images.stream().map(Image::getId).toList();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Проверяем, что пользователь аутентифицирован
        if (authentication != null && authentication.isAuthenticated()) {
            // Получаем имя пользователя
            String username = authentication.getName();
            // Добавляем имя пользователя в модель
            model.addAttribute("username", username);
        }

        model.addAttribute("imageIds", imageIds);
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        model.addAttribute("category", category);
        model.addAttribute("catalogList", catalogList);
        model.addAttribute("colorList", colorList);
        model.addAttribute("sizesList", sizeList);

        return "product";
    }

    @GetMapping("/price")
    @ResponseBody
    public Map<String, Object> getPrice(
            @PathVariable("productId") Long productId,
            @PathVariable("categoryId") Long categoryId,
            @RequestParam("color_id") Long colorId,
            @RequestParam("size_id") Long sizeId) {

        Long price = catalogService.findPriceByCategoryIdAndProductIdAndColorIdAndSizeId(
                categoryId, productId, colorId, sizeId);


        Long stock = catalogService.findStockByCategoryIdAndProductIdAndColorIdAndSizeId(
                categoryId, productId, colorId, sizeId);


        Map<String, Object> response = new HashMap<>();
        response.put("price", price);
        response.put("stock", stock);

        return response;
    }

}