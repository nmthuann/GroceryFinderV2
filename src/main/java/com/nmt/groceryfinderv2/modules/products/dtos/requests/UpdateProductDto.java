package com.nmt.groceryfinderv2.modules.products.dtos.requests;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

/**
 * @author LENOVO
 * @project GroceryFinderV2
 * @date 11/15/2024
 */
public record UpdateProductDto(
        @NotBlank(message = "Barcode is required")
        @Size(max = 20, message = "Barcode cannot exceed 20 characters")
        String barcode,
        @NotEmpty
        String productName,
        @URL
        String productThumb,
        @NotNull(message = "Sale price is required")
        @PositiveOrZero(message = "Sale Price Price must be zero or greater")
        Double salePrice,
        @NotNull(message = "Import price is required")
        @PositiveOrZero(message = "Import Price must be zero or greater")
        Double importPrice,
        @NotEmpty
        String description,
        @NotEmpty
        String category,
        @NotEmpty
        String unit,
        @NotNull(message = "Display price is required")
        @PositiveOrZero(message = "Display Price must be zero or greater")
        Integer stock,
        @NotNull(message = "Display price is required")
        @PositiveOrZero(message = "Display Price must be zero or greater")
        Integer sold,
        @NotNull
        Boolean isActive
) {
}

