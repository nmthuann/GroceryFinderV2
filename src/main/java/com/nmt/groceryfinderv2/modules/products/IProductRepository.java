package com.nmt.groceryfinderv2.modules.products;

import com.nmt.groceryfinderv2.modules.products.domain.ProductDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author LENOVO
 * @project GroceryFinderV2
 * @date 11/13/2024
 */
@Repository
public interface IProductRepository extends MongoRepository<ProductDocument, String> {
    Page<ProductDocument> findAll(Pageable pageable);
}
