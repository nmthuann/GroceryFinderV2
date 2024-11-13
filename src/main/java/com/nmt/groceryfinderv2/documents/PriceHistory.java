package com.nmt.groceryfinderv2.documents;

import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

/**
 * @author LENOVO
 * @project GroceryFinderV2
 * @date 11/13/2024
 */
public record PriceHistory(
        @Field("historical_price") Double historicalPrice,
        @Field("change_date") LocalDateTime changeDate,
        @Field("previous_import_price") Double previousImportPrice
) {}
