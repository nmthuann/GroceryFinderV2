package com.nmt.groceryfinderv2.modules.products.documents;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.List;
import java.util.Map;

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
    private String barcode; // 1

    @Field("product_name")
    @Indexed(unique = true)
    private String productName; // 2

    @Indexed
    private String category; // 3

    @Field("product_thumb")
    private String productThumb; // 4

    @Field("sale_price")
    private Double salePrice; // 5

    @Field("import_price")
    private Double importPrice; // 6

    private Integer sold;  // 7

    private Integer stock; // 8

    private String unit; // 9

    private String description; // 10

    @Indexed(unique = true)
    private String slug;

    @Field("normalized_name")
    private String normalizedName;

    @Field("category_url")
    private String categoryUrl;

    @Field("is_active")
    private Boolean isActive;

    @Field("view_count")
    private Integer viewCount = 0;

    private Integer like;

    private List<Specification> specs;

    @Field("price_history")
    private List<?> priceHistory;

    private List<?> reviews;

    private Map<String, Object> metadata;
}
