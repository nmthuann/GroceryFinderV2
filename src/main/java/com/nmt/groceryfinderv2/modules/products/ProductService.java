package com.nmt.groceryfinderv2.modules.products;

import com.nmt.groceryfinderv2.modules.products.domain.ProductDocument;
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
public class ProductService implements IProductService{

    private final IProductRepository productRepository;

    @Autowired
    public  ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public ProductDocument createOne(ProductDocument productDocument) {
        return productRepository.save(productDocument);
    }

    @Override
    public ProductDocument updateOneById(String id, ProductDocument updatedProduct) {
        return null;
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
    public List<ProductDocument> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<ProductDocument> getPaginated(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<ProductDocument> getOneById(String id) {
        return productRepository.findById(id);
    }
}
