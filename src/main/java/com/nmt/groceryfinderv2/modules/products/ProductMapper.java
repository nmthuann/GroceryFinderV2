package com.nmt.groceryfinderv2.modules.products;

import com.nmt.groceryfinderv2.modules.products.documents.ProductDocument;
import com.nmt.groceryfinderv2.modules.products.dtos.requests.CreateProductDto;
import com.nmt.groceryfinderv2.modules.products.dtos.ProductDto;
import com.nmt.groceryfinderv2.utils.SlugUtil;
import org.springframework.stereotype.Component;
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
        target.setSellingPrice(source.getSellingPrice());
        target.setSold(source.getSold());
        target.setIsActive(source.getIsActive());
    }

    public ProductDocument createDocument (CreateProductDto data) {
        Objects.requireNonNull(data, "CreateProductDto must not be null");
        ProductDocument productDocument = new ProductDocument();
        productDocument.setSlug(SlugUtil.createSlug(data.productName()));
        productDocument.setBarcode(data.barcode());
        productDocument.setProductName(data.productName());
        productDocument.setNormalizedName(SlugUtil.replaceVietnameseChars(data.productName()));
        productDocument.setProductThumb(data.productThumb());
        productDocument.setSellingPrice(data.sellingPrice());
        productDocument.setImportPrice(data.importPrice());
        productDocument.setDescription(data.description());
        productDocument.setCategory(data.category());
        productDocument.setUnit(data.unit());
        productDocument.setCategoryUrl(SlugUtil.createSlug(data.category()));
        productDocument.setStock(data.stock());
        productDocument.setSold(0);
        productDocument.setLike(0);
        productDocument.setViewCount(0);
        productDocument.setIsActive(true);
        productDocument.setSpecs(data.specs());
        return productDocument;
    }
}