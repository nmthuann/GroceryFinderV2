package com.nmt.groceryfinderv2.modules.products;

import com.nmt.groceryfinderv2.exceptions.ModuleException;
import com.nmt.groceryfinderv2.modules.products.domain.ProductDocument;
import com.nmt.groceryfinderv2.modules.products.dtos.CreateProductDto;
import com.nmt.groceryfinderv2.modules.products.dtos.ProductDto;
import com.nmt.groceryfinderv2.modules.products.dtos.UpdateProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author LENOVO
 * @project GroceryFinderV2
 * @date 11/14/2024
 */
public interface IProductService {
    ProductDto createOne(CreateProductDto data);
    ProductDto updateOneById(String id, UpdateProductDto data) throws ModuleException;
    Boolean deleteOneById(String id);
    List<ProductDto> getAll();
    Page<ProductDto> getPaginated(Pageable pageable);
    Optional<ProductDocument> getOneById(String id);
    Optional<ProductDocument> getOneBySlug(String slug);
    Optional<ProductDocument> getOneByBarcode(String barcode);
    List<ProductDto> getProductsByBrand(String brand);
    List<ProductDto> getProductsByCategory(String category);
    Boolean checkProductNameDuplicate(String productName);
}
