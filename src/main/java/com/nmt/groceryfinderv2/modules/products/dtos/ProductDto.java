package com.nmt.groceryfinderv2.modules.products.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * @author LENOVO
 * @project GroceryFinderV2
 * @date 11/15/2024
 */
@Getter
@Setter
public class ProductDto {
    private String id;
    private String slug;
    private String barcode;
    private String productName;
    private String productThumb;
    private Double displayPrice;
    private Integer sold;
    private String currency;
    private Boolean isActive;

}