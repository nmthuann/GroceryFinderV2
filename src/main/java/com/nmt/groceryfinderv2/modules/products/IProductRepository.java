package com.nmt.groceryfinderv2.modules.products;

import com.nmt.groceryfinderv2.modules.products.documents.ProductDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author LENOVO
 * @project GroceryFinderV2
 * @date 11/13/2024
 */
@Repository
public interface IProductRepository extends MongoRepository<ProductDocument, String> {
    Page<ProductDocument> findAll(Pageable pageable);
    Optional<ProductDocument> findBySlug(String slug);
    Optional<ProductDocument> findByBarcode(String barcode);
    List<ProductDocument> findByBrand(String brand);
    List<ProductDocument> findByCategory(String category);
    Optional<ProductDocument> findByProductName(String productName);
}
