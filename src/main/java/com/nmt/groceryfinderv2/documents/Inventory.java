package com.nmt.groceryfinderv2.documents;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

/**
 * @author LENOVO
 * @project GroceryFinderV2
 * @date 11/13/2024
 */
@Getter
@Setter
public class Inventory {
    @Field("stock_quantity")
    private Integer stockQuantity;

    @Field("defective_quantity")
    private Integer defectiveQuantity;

    @Field("min_stock_quantity")
    private Integer minStockQuantity;

    @Field("last_inventory_check_date")
    private LocalDateTime lastInventoryCheckDate;

    @Field("supplier_name")
    private String supplierName;

    @Field("supplier_address")
    private String supplierAddress;
}
