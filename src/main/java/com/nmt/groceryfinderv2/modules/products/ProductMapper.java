package com.nmt.groceryfinderv2.modules.products;

import com.nmt.groceryfinderv2.modules.products.documents.ProductDocument;
import com.nmt.groceryfinderv2.modules.products.dtos.requests.CreateProductDto;
import com.nmt.groceryfinderv2.modules.products.dtos.ProductDto;
import com.nmt.groceryfinderv2.modules.products.dtos.requests.UpdateProductDto;
import com.nmt.groceryfinderv2.utils.SlugUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author LENOVO
 * @project GroceryFinderV2
 * @date 11/15/2024
 */
@Component
public class ProductMapper {

    public ProductDto toDto (ProductDocument productDocument) {
        Objects.requireNonNull(productDocument, "ProductDocument must not be null");
        ProductDto productDto = new ProductDto();
        copyCommonProperties(productDocument, productDto);
        return productDto;
    }

    private void copyCommonProperties(ProductDocument source, ProductDto target) {
        target.setId(source.getId());
        target.setSlug(source.getSlug());
        target.setBarcode(source.getBarcode());
        target.setProductName(source.getProductName());
        target.setProductThumb(source.getProductThumb());
        target.setCategoryId(source.getCategoryId());
        target.setSellingPrice(source.getSellingPrice());
        target.setImportPrice(source.getDisplayPrice());
        target.setIsActive(source.getIsDeleted());
    }

    public ProductDocument createDocument (CreateProductDto data) {
        Objects.requireNonNull(data, "CreateProductDto must not be null");
        ProductDocument productDocument = new ProductDocument();
        productDocument.setSlug(SlugUtil.createSlug(data.productName()));
        productDocument.setBarcode(data.barcode());
        productDocument.setProductName(data.productName());
        productDocument.setProductThumb(data.productThumb());
        productDocument.setSellingPrice(data.salePrice());
        productDocument.setDisplayPrice(data.importPrice());
        productDocument.setCategoryId(data.category());
        productDocument.setIsDeleted(true);
        productDocument.setSpecs(new ArrayList<>());
        productDocument.setPriceHistory(new ArrayList<>());
        productDocument.setMetadata(new HashMap<>());
        return productDocument;
    }

    public ProductDocument updateDocument(UpdateProductDto data, ProductDocument productCreated){
        Objects.requireNonNull(data, "UpdateProductDto must not be null");
        Objects.requireNonNull(productCreated, "Existing ProductDocument must not be null");

        productCreated.setBarcode(data.barcode());
        productCreated.setProductName(data.productName());
        productCreated.setProductThumb(data.productThumb());
        productCreated.setSellingPrice(data.salePrice());
        productCreated.setDisplayPrice(data.importPrice());
        productCreated.setCategoryId(data.category());
        productCreated.setIsDeleted(data.isActive());

        return productCreated;
    }
}