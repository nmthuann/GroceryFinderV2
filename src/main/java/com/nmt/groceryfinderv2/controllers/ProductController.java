package com.nmt.groceryfinderv2.controllers;

import com.nmt.groceryfinderv2.documents.ProductDocument;
import com.nmt.groceryfinderv2.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LENOVO
 * @project GroceryFinderV2
 * @date 11/13/2024
 */
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public ResponseEntity<List<ProductDocument>> getAllProducts() {
        List<ProductDocument> products = productService.findAllProducts();
        return ResponseEntity.ok(products);
    }
}
