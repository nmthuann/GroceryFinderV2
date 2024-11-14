package com.nmt.groceryfinderv2.modules.products;

import com.nmt.groceryfinderv2.modules.products.domain.ProductDocument;
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
    ProductDocument createOne(ProductDocument productDocument);
    ProductDocument updateOneById(String id, ProductDocument updatedProduct);
    Boolean deleteOneById(String id);
    List<ProductDocument> getAll();
    Page<ProductDocument> getPaginated(Pageable pageable);
    Optional<ProductDocument> getOneById(String id);
}
