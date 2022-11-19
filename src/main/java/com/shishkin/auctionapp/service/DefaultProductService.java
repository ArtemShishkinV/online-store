package com.shishkin.auctionapp.service;

import com.shishkin.auctionapp.entity.ProductEntity;
import com.shishkin.auctionapp.entity.udt.CategoryUdt;
import com.shishkin.auctionapp.exception.CategoryNotFoundException;
import com.shishkin.auctionapp.exception.ProductNotFoundException;
import com.shishkin.auctionapp.mapper.entity.ProductToEntityMapper;
import com.shishkin.auctionapp.model.Product;
import com.shishkin.auctionapp.repository.CategoryRepository;
import com.shishkin.auctionapp.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class DefaultProductService implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductToEntityMapper productMapper;

    @Override
    public List<Product> findAll() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .map(productMapper::productEntityToProduct).toList();
    }

    @Override
    public Product findById(Long id) {
        return productMapper.productEntityToProduct(productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, "Product not found: id = " + id)));
    }

    @Override
    public ProductEntity add(Product product) {
        ProductEntity productEntity = this.productMapper.productToProductEntity(product);
        productEntity.setCategory(new CategoryUdt(product.getCategoryTitle()));

        return productRepository.save(productEntity);
    }
}
