package com.nmt.groceryfinderv2.repositories;

import com.nmt.groceryfinderv2.documents.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author LENOVO
 * @project GroceryFinderV2
 * @date 11/13/2024
 */
@Repository
public interface IProductRepository extends MongoRepository<ProductDocument, String> {
}
