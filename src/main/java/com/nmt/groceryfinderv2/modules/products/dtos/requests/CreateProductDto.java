package com.nmt.groceryfinderv2.modules.products.dtos.requests;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

/**
 * @author LENOVO
 * @project GroceryFinderV2
 * @date 11/15/2024
 */
public record CreateProductDto(
        @NotBlank(message = "Barcode is required")
        @Size(max = 20, message = "Barcode cannot exceed  characters")
        String barcode, // 0
        @Size(max = 100, message = "Product name too long")
        @NotEmpty
        String productName, // 1
        @URL
        String productThumb, // 2
        @NotNull(message = "Selling price is required")
        @PositiveOrZero(message = "Selling Price must be zero or greater")
        Double salePrice, // 3
        @PositiveOrZero(message = "Import price must be zero or greater")
        Double importPrice,// 4
        @NotEmpty
        String description, // 5
        @NotEmpty
        String category, // 6
        @NotEmpty
        String unit, // 7
        @PositiveOrZero(message = "Stock must be zero or greater")
        Integer stock// 8
) {
}
