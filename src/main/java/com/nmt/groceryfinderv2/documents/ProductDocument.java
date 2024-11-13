package com.nmt.groceryfinderv2.documents;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDateTime;
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

    private String slug;

    @Field("product_name")
    private String productName;

    @Field("normalized_name")
    private String normalizedName;

    @Field("product_thumb")
    private String productThumb;

    @Field("display_price")
    private Double displayPrice;

    @Field("import_price")
    private Double importPrice;

    private String description;

    private String currency;

    private Integer sold;

    @Field("view_count")
    private Integer viewCount = 0;

    @Field("is_active")
    private Boolean isActive;

    private List<Specification> specs;

    @Field("price_history")
    private List<PriceHistory> priceHistory;

    @Field("inventory")
    private Inventory inventory;

    @Field("created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Field("updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
