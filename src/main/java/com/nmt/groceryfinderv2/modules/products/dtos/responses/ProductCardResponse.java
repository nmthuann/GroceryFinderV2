package com.nmt.groceryfinderv2.modules.products.dtos.responses;

/**
 * @author LENOVO
 * @project GroceryFinder
 * @date 11/25/2024
 */
public record ProductCardResponse(
        Integer id,
        String slug,
        String productName,
        String productThumb,
        String category,
        String categoryUrl,
        Boolean isActive,
        Integer sold,
        Integer currentStock,
        Double latestPrice,
        Double oldPrice,
        Integer like
) {
}