package com.nmt.groceryfinderv2.modules.products.dtos;

import com.nmt.groceryfinderv2.modules.products.domain.Specification;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * @author LENOVO
 * @project GroceryFinderV2
 * @date 11/15/2024
 */
public record UpdateProductDto(
        String barcode,
        String productName,
        String productThumb,
        Double displayPrice,
        Double importPrice,
        String description,
        String category,
        String brand,
        String currency,
        Integer stock,
        Integer sold,
        Boolean isActive,
        List<Specification> specs
) {
}

