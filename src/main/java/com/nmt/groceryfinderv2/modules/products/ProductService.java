package com.nmt.groceryfinderv2.modules.products;

import com.nmt.groceryfinderv2.exceptions.ModuleException;
import com.nmt.groceryfinderv2.exceptions.messages.ProductsModuleExceptionMessages;
import com.nmt.groceryfinderv2.modules.products.documents.ProductDocument;
import com.nmt.groceryfinderv2.modules.products.documents.Specification;
import com.nmt.groceryfinderv2.modules.products.dtos.requests.CreateProductDto;
import com.nmt.groceryfinderv2.modules.products.dtos.ProductDto;
import com.nmt.groceryfinderv2.modules.products.dtos.requests.UpdateProductDto;
import com.nmt.groceryfinderv2.utils.JsonUtil;
import com.nmt.groceryfinderv2.utils.SlugUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author LENOVO
 * @project GroceryFinderV2
 * @date 11/13/2024
 */
@Service
@Slf4j
public class ProductService implements IProductService {

    private final IProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public  ProductService(
            IProductRepository productRepository,
            ProductMapper productMapper
    ) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    @Override
    public  Optional<ProductDocument> getOneById(String id) {
        return productRepository.findById(id);
    }
    @Override
    public Optional<ProductDocument> getOneBySlug(String slug) {
        return productRepository.findBySlug(slug);
    }
    @Override
    public Optional<ProductDocument> getOneByBarcode(String barcode) {
        return productRepository.findByBarcode(barcode);
    }

    @Override
    public ProductDto createOne(CreateProductDto data) throws ModuleException {
        if (this.getOneByBarcode(data.barcode()).isPresent()) {
            throw new ModuleException(
                    ProductsModuleExceptionMessages.GET_PRODUCT_BARCODE_ALREADY_EXISTS.getMessage()
            );
        }if (this.checkProductNameDuplicate(data.productName())) {
            throw new ModuleException(
                    ProductsModuleExceptionMessages.GET_PRODUCT_NAME_ALREADY_EXISTS.getMessage()
            );
        }
        ProductDocument createProduct = this.productMapper.createDocument(data);
        return this.productMapper.toDto(this.productRepository.save(createProduct));
    }

    @Override
    public ProductDto updateOneById(String id, UpdateProductDto data) throws ModuleException {
        Optional<ProductDocument> productOpt = productRepository.findById(id);
        if (productOpt.isEmpty()) {
            throw new ModuleException(
                    ProductsModuleExceptionMessages.GET_PRODUCT_ID_NOT_FOUND.getMessage() + "ID: " +id
            );
        }
        ProductDocument productCreated = productOpt.get();
        if (data.productName() != null) {
            productCreated.setSlug(SlugUtil.createSlug(data.productName()));
            productCreated.setNormalizedName(SlugUtil.replaceVietnameseChars(data.productName()));
            productCreated.setProductName(data.productName());
        }

        if (data.barcode() != null) {
            productCreated.setBarcode(data.barcode());
        }

        if (data.productThumb() != null) {
            productCreated.setProductThumb(data.productThumb());
        }

        if (data.sellingPrice() != null) {
            productCreated.setSellingPrice(data.sellingPrice());
        }

        if (data.importPrice() != null) {
            productCreated.setImportPrice(data.importPrice());
        }

        if (data.description() != null) {
            productCreated.setDescription(data.description());
        }

        if (data.category() != null) {
            productCreated.setCategory(data.category());
        }

        if (data.stock() != null) {
            productCreated.setStock(data.stock());
        }

        if (data.sold() != null) {
            productCreated.setSold(data.sold());
        }

        if (data.isActive() != null) {
            productCreated.setIsActive(data.isActive());
        }

        if (data.specs() != null) {
            productCreated.setSpecs(data.specs());
        }
        return this.productMapper.toDto(this.productRepository.save(productCreated));
    }


    @Override
    public Boolean deleteOneById(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }


    @Override
    public Page<ProductDto> getPaginated(Pageable pageable) {
        // đọc cache
        return productRepository.findAll(pageable).map(
                this.productMapper::toDto
        );
    }



    @Override
    public List<ProductDto> getProductsByCategory(String category) {
        // đọc cache
        return this.productRepository.findByCategory(category).stream().map(
                this.productMapper::toDto
        ).toList();
    }

    @Override
    public Boolean checkProductNameDuplicate(String productName) {
        return productRepository.findByProductName(productName).isPresent();
    }

    @Override
    @Transactional
    public List<ProductDto> importProductsFromCSV(Iterable<CSVRecord> records) throws ModuleException {
        List<ProductDocument> productDocuments = new ArrayList<>();
        for (CSVRecord record : records) {
            String barcode = record.get(0).replaceAll("^\"|\"$", "");
            if (this.getOneByBarcode(barcode).isPresent()) {
                throw new ModuleException("barcode with name '" + barcode + "' already exists.");
            }
            String productName = record.get(1);
            if (this.checkProductNameDuplicate(productName)) {
                throw new ModuleException("Product with name '" + productName + "' already exists.");
            }
            String productThumb = record.get(2);
            Double displayPrice = Double.parseDouble(record.get(3));
            Double importPrice = Double.parseDouble(record.get(4));
            String description = record.get(5);
            String category = record.get(6);
            String brand = record.get(7);
            Integer stock = Integer.parseInt(record.get(8));
            String specsJson = record.get(9);

            List<Specification> specsList = null;
            if (specsJson != null && !specsJson.isEmpty()) {
                specsList = JsonUtil.parseKeyAndValuePair(specsJson);
            }

            CreateProductDto createProductDto = new CreateProductDto(
                    barcode,
                    productName,
                    productThumb,
                    displayPrice,
                    importPrice,
                    description,
                    category,
                    brand,
                    stock,
                    specsList
            );
            ProductDocument productDocument = this.productMapper.createDocument(createProductDto);
            productDocuments.add(productDocument);
        }

        List<ProductDocument> productDocumentListCreated = this.productRepository.saveAll(productDocuments);
        return productDocumentListCreated.stream().map(
                this.productMapper::toDto
        ).toList();
    }
}
