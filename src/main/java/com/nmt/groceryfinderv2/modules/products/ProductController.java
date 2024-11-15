package com.nmt.groceryfinderv2.modules.products;

import com.nmt.groceryfinderv2.modules.products.domain.ProductDocument;
import com.nmt.groceryfinderv2.modules.products.dtos.CreateProductDto;
import com.nmt.groceryfinderv2.modules.products.dtos.ProductDto;
import com.nmt.groceryfinderv2.shared.logging.LoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


/**
 * @author LENOVO
 * @project GroceryFinderV2
 * @date 11/13/2024
 */
@RestController
@RequestMapping("/v1/products")
public class ProductController {
    private final IProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    @LoggingInterceptor
    public ResponseEntity<ProductDto> createProduct(@RequestBody CreateProductDto product) {
        ProductDto savedProduct = this.productService.createOne(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @LoggingInterceptor
    public ResponseEntity<ProductDocument> getProductById(@PathVariable String id) {
        ProductDocument product = productService.getOneById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable String id) {
        Boolean isDeleted = productService.deleteOneById(id);
        if (isDeleted) {
            return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping
    public ResponseEntity<?> getProducts(
            @RequestParam(required = false) String slug,
            @RequestParam(required = false) String barcode,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "true") Boolean isPagination
    ) {
        try {
            if (!isPagination) {
                if (slug != null) {
                    Optional<ProductDocument> product = productService.getOneBySlug(slug);
                    if (product.isPresent()) {
                        return new ResponseEntity<>(product.get(), HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                    }
                } else if (barcode != null) {
                    Optional<ProductDocument> product = productService.getOneByBarcode(barcode);
                    if (product.isPresent()) {
                        return new ResponseEntity<>(product.get(), HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                    }
                } else if (brand != null) {
                    List<ProductDto> products = productService.getProductsByBrand(brand);
                    return new ResponseEntity<>(products, HttpStatus.OK);
                } else if (category != null) {
                    List<ProductDto> products = productService.getProductsByCategory(category);
                    return new ResponseEntity<>(products, HttpStatus.OK);
                }
                else {
                    List<ProductDto> products = productService.getAll();
                    return new ResponseEntity<>(products, HttpStatus.OK);
                }
            }
            else {
                Pageable pageable = PageRequest.of(page, size);
                Page<ProductDto> productPage = productService.getPaginated(pageable);
                return new ResponseEntity<>(productPage, HttpStatus.OK);
            }
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }



    @GetMapping("/check")
    public ResponseEntity<?> checkProductName(@RequestParam String productName) {
        if (productService.checkProductNameDuplicate(productName)) {
            // Nếu tên đã tồn tại
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Product with this name already exists");
        }
        return ResponseEntity.ok("Product name is available");
    }
}
