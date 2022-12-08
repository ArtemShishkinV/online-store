package com.shishkin.auctionapp.service;

import com.shishkin.auctionapp.entity.CategoryEntity;
import com.shishkin.auctionapp.entity.ProductEntity;
import com.shishkin.auctionapp.exception.CategoryNotFoundException;
import com.shishkin.auctionapp.exception.ProductNotFoundException;
import com.shishkin.auctionapp.mapper.entity.ProductToEntityMapper;
import com.shishkin.auctionapp.model.Category;
import com.shishkin.auctionapp.model.Product;
import com.shishkin.auctionapp.repository.CategoryRepository;
import com.shishkin.auctionapp.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class DefaultProductService implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductToEntityMapper productMapper;

    private final Random random = new Random();

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
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public ProductEntity add(Product product) throws CategoryNotFoundException {
        ProductEntity productEntity = this.productMapper.productToProductEntity(product);
        try {
            productEntity.setCategoryId(categoryRepository.findByTitle(product.getCategoryTitle())
                    .orElseThrow().getId());
        } catch (SQLException e) {
            throw new CategoryNotFoundException(HttpStatus.NOT_FOUND,
                    "Category not found: title = " + product.getCategoryTitle(), e);
        }

        return productRepository.save(productEntity);
    }

    @Override
    public List<ProductEntity> saveAll(List<ProductEntity> products) {
        return StreamSupport.stream(productRepository.saveAll(products).spliterator(), false).toList();
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return StreamSupport.stream(productRepository.findByCategory(category.getId()).spliterator(), false)
                .map(productMapper::productEntityToProduct).toList();
    }

    public List<ProductEntity> generate(int count) {
        final List<ProductEntity> products = new ArrayList<>(count);
        final List<CategoryEntity> categories = StreamSupport.stream(
                this.categoryRepository.findAll().spliterator(),
                false).toList();

        for (int i = 0; i < count; i++) {
            products.add(generate(categories));
        }

        return products;
    }

    private ProductEntity generate(List<CategoryEntity> categories) {
        Product product = new Product();
        product.setTitle("Продукт-" + random.nextInt(20));
        product.setCategoryTitle(categories.get(random.nextInt(categories.size())).getTitle());
        product.setDescription("description");
        product.setPrice(random.nextLong(1000, 20000));

        return this.productMapper.productToProductEntity(product);
    }
}
