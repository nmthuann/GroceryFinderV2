package com.nmt.groceryfinderv2.modules.products;

import com.nmt.groceryfinderv2.exceptions.ModuleException;
import com.nmt.groceryfinderv2.modules.products.documents.ProductDocument;
import com.nmt.groceryfinderv2.modules.products.dtos.CreateProductDto;
import com.nmt.groceryfinderv2.modules.products.dtos.ProductDto;
import com.nmt.groceryfinderv2.modules.products.dtos.UpdateProductDto;
import com.nmt.groceryfinderv2.shared.logging.LoggingInterceptor;
import com.nmt.groceryfinderv2.utils.FileUtil;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    public ResponseEntity<ProductDto> createOne(@RequestBody CreateProductDto product) throws ModuleException {
        ProductDto savedProduct = this.productService.createOne(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }


    @PatchMapping("/{id}")
    @LoggingInterceptor
    public ResponseEntity<ProductDto> updateOneById(
            @PathVariable String id,
            @RequestBody UpdateProductDto productData
    ) {
        try {
            ProductDto updatedProduct = this.productService.updateOneById(id, productData);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (ModuleException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{id}")
    @LoggingInterceptor
    public ResponseEntity<ProductDocument> getOneById(@PathVariable String id) {
        ProductDocument product = productService.getOneById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @LoggingInterceptor
    public ResponseEntity<Boolean> deleteOneById(@PathVariable String id) {
        Boolean isDeleted = productService.deleteOneById(id);
        if (isDeleted) {
            return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping
    @LoggingInterceptor
    public ResponseEntity<?> getProducts(
            @RequestParam(required = false) String slug, // 1
            @RequestParam(required = false) String barcode, // 2
            @RequestParam(required = false) String category, // 4
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "true") Boolean isPagination
    ) {
        // 5 API
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
                } else if (category != null) {
                    List<ProductDto> products = productService.getProductsByCategory(category);
                    return new ResponseEntity<>(products, HttpStatus.OK);
                }
                else {
                    return new ResponseEntity<>("Missing the Request Param", HttpStatus.BAD_REQUEST);
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
    @LoggingInterceptor
    public ResponseEntity<?> checkProductName(@RequestParam String productName) {
        if (productService.checkProductNameDuplicate(productName)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Product with this name already exists");
        }
        return ResponseEntity.ok("Product name is available");
    }


    @PostMapping("/upload")
    @LoggingInterceptor
    public ResponseEntity<?> uploadCsv(@RequestParam("file") MultipartFile file) throws ModuleException{
        List<CSVRecord> records = FileUtil.readCsvFile(file);
        return new ResponseEntity<>(
            this.productService.importProductsFromCSV(records),
            HttpStatus.OK
        );
    }
}
