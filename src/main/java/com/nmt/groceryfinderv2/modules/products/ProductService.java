package com.nmt.groceryfinderv2.modules.products;

import com.nmt.groceryfinderv2.exceptions.ModuleException;
import com.nmt.groceryfinderv2.modules.products.domain.ProductDocument;
import com.nmt.groceryfinderv2.modules.products.domain.Specification;
import com.nmt.groceryfinderv2.modules.products.dtos.CreateProductDto;
import com.nmt.groceryfinderv2.modules.products.dtos.ProductDto;
import com.nmt.groceryfinderv2.modules.products.dtos.UpdateProductDto;
import com.nmt.groceryfinderv2.utils.SlugUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author LENOVO
 * @project GroceryFinderV2
 * @date 11/13/2024
 */
@Service
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
    public ProductDto createOne(CreateProductDto data) {
        ProductDocument createProduct = this.productMapper.createDocument(data);
        return this.productMapper.toDto(this.productRepository.save(createProduct));
    }

    @Override
    public ProductDto updateOneById(String id, UpdateProductDto data) throws ModuleException {
        Optional<ProductDocument> productOpt = productRepository.findById(id);
        if (productOpt.isEmpty()) {
            throw new ModuleException("Product with id " + id + " not found.");
        }
        ProductDocument productCreated = productOpt.get();
        if (data.productName() != null) {
            productCreated.setSlug(SlugUtil.createSlug(data.productName()));  // Cập nhật slug
            productCreated.setNormalizedName(SlugUtil.replaceVietnameseChars(data.productName()));  // Cập nhật tên chuẩn hóa
            productCreated.setProductName(data.productName());
        }

        if (data.barcode() != null) {
            productCreated.setBarcode(data.barcode());
        }

        if (data.productThumb() != null) {
            productCreated.setProductThumb(data.productThumb());
        }

        if (data.displayPrice() != null) {
            productCreated.setDisplayPrice(data.displayPrice());
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

        if (data.brand() != null) {
            productCreated.setBrand(data.brand());
        }

        if (data.currency() != null) {
            productCreated.setCurrency(data.currency());
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
    public List<ProductDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(this.productMapper::toDto)
                .toList();
    }

    @Override
    public Page<ProductDto> getPaginated(Pageable pageable) {
        return productRepository.findAll(pageable).map(
                this.productMapper::toDto
        );
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
    public List<ProductDto> getProductsByBrand(String brand) {
        return this.productRepository.findByBrand(brand).stream().map(
                this.productMapper::toDto
        ).toList();
    }

    @Override
    public List<ProductDto> getProductsByCategory(String category) {
        return this.productRepository.findByCategory(category).stream().map(
                this.productMapper::toDto
        ).toList();
    }

    @Override
    public Boolean checkProductNameDuplicate(String productName) {
        return productRepository.findByProductName(productName).isPresent();
    }
}
