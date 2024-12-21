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
        @Size(max = 20, message = "Barcode cannot exceed 20 characters")
        String barcode,
        @Size(max = 100, message = "Product name too long")
        @NotEmpty
        String productName,
        @URL
        String productThumb,
        @NotNull(message = "Display price is required")
        @PositiveOrZero(message = "Display Price must be zero or greater")
        Double latestPrice,
        @PositiveOrZero(message = "Import price must be zero or greater")
        Double oldPrice,
        String description,
        @NotEmpty
        String category,
        @PositiveOrZero(message = "Stock must be zero or greater")
        Integer stock,
        List<Specification> specs
) {
}
