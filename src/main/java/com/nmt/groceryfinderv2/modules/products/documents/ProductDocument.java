package com.nmt.groceryfinderv2.modules.products.documents;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.List;

/**
 * @author LENOVO
 * @project GroceryFinderV2
 * @date 11/12/2024
 */
@Document(collection = "products")
@Getter
@Setter
public class ProductDocument {
    @Id
    private String id;

    @Indexed(unique = true)
    private String barcode;

    @Indexed(unique = true)
    private String slug;// 1

    @Field("product_name")
    @Indexed(unique = true)
    private String productName;

    @Field("normalized_name")
    private String normalizedName;// 2

    @Field("product_thumb")
    private String productThumb;

    @Field("display_price")
    private Double displayPrice;

    @Field("import_price")
    private Double importPrice;

    @Indexed
    private String category;

    @Indexed
    private String brand;

    private String currency;

    private Integer sold; // 3

    private Integer stock;

    private String description;

    @Field("is_active")
    private Boolean isActive; // 5

    @Field("view_count")
    private Integer viewCount = 0; // 4

    private List<Specification> specs;
}
