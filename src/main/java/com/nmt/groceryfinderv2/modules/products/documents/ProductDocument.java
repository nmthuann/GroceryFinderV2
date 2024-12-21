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
    private String slug;

    @Field("product_name")
    @Indexed(unique = true)
    private String productName;

    @Field("normalized_name")
    private String normalizedName;

    @Indexed
    private String category;

    @Field("category_url")
    private String categoryUrl;

    @Field("product_thumb")
    private String productThumb;

    @Field("latest_price")
    private Double latestPrice;

    @Field("old_price")
    private Double oldPrice;

    private Integer sold;

    private Integer stock;

    private Integer like;

    private String description;

    private String unit;

    @Field("is_active")
    private Boolean isActive;

    @Field("view_count")
    private Integer viewCount = 0;

    private List<Specification> specs;
}
