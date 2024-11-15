package com.nmt.groceryfinderv2.modules.products.dtos;

import com.nmt.groceryfinderv2.modules.products.domain.Specification;

import java.util.List;

/**
 * @author LENOVO
 * @project GroceryFinderV2
 * @date 11/15/2024
 */
public record CreateProductDto(
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
        List<Specification> specs
) {
}
