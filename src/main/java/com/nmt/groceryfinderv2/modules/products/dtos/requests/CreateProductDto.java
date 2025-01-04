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
public record CreateProductDto(
        @NotBlank(message = "Barcode is required")
        @Size(max = 20, message = "Barcode cannot exceed  characters")
        String barcode,
        @Size(max = 100, message = "Product name too long")
        @NotEmpty
        String productName,
        @URL
        String productThumb,
        @NotNull(message = "Selling price is required")
        @PositiveOrZero(message = "Selling Price must be zero or greater")
        Double salePrice,
        @PositiveOrZero(message = "Import price must be zero or greater")
        Double importPrice,
        @NotEmpty
        String description,
        @NotEmpty
        String category,
        @NotEmpty
        String unit,
        @PositiveOrZero(message = "Stock must be zero or greater")
        Integer stock
) {
}
