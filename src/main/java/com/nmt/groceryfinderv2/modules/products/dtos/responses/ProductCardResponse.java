package com.nmt.groceryfinderv2.modules.products.dtos.responses;

import java.util.UUID;

/**
 * @author LENOVO
 * @project GroceryFinder
 * @date 11/25/2024
 */
public record ProductCardResponse(
        Integer id,
        String slug,
        String productName,
        String image,
        Boolean status,
        Integer sold,
        Integer currentStock,
        Double latestPrice,
        Double oldPrice,
        String unit
) {
}
