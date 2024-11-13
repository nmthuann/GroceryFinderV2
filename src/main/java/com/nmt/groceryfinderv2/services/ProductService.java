package com.nmt.groceryfinderv2.services;

import com.nmt.groceryfinderv2.documents.ProductDocument;
import com.nmt.groceryfinderv2.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LENOVO
 * @project GroceryFinderV2
 * @date 11/13/2024
 */
@Service
public class ProductService {

    private final IProductRepository productRepository;

    @Autowired
    public  ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDocument> findAllProducts() {
        return productRepository.findAll();
    }
}
