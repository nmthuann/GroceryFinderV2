package com.nmt.groceryfinderv2.modules.products.dtos.requests;

import com.nmt.groceryfinderv2.modules.products.documents.Specification;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.util.List;

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
        @NotNull(message = "Display price is required")
        @PositiveOrZero(message = "Latest Price Price must be zero or greater")
        Double latestPrice,
        @NotNull(message = "Display price is required")
        @PositiveOrZero(message = "Display Price must be zero or greater")
        Double oldPrice,
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
        Boolean isActive,
        List<Specification> specs
) {
}

