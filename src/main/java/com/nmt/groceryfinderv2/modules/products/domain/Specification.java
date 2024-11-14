package com.nmt.groceryfinderv2.modules.products.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author LENOVO
 * @project GroceryFinderV2
 * @date 11/12/2024
 */
@Getter
@Setter
public class Specification {
    @Field("k")
    private String k;

    @Field("v")
    private String v;
}
