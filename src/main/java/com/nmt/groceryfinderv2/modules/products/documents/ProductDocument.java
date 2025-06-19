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

    @Indexed(unique = true)
    private String slug;

    @Field("product_name")
    @Indexed(unique = true)
    private String productName; // 2

    @Field("product_thumb")
    private String productThumb; // 3

    @Field("selling_price")
    private Double sellingPrice; // 4

    @Field("display_price")
    private Double displayPrice; // 5

//    private String description; // 6

    @Field("category_id")
    private String categoryId;

    @Field("is_deleted")
    private Boolean isDeleted;

    private List<Specification> specs;

    @Field("price_history")
    private List<?> priceHistory;

    private Map<String, Object> metadata;
}
