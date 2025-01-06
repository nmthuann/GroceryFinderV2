package com.nmt.groceryfinderv2.modules.products.dtos;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author LENOVO
 * @project GroceryFinderV2
 * @date 1/5/2025
 */
public record GenerateProductDto(

       String slug,
       String normalizedName,
       String categoryUrl
) {
}
