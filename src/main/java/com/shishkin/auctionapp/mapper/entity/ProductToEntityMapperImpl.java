package com.shishkin.auctionapp.mapper.entity;

import com.shishkin.auctionapp.entity.ProductEntity;
import com.shishkin.auctionapp.model.Product;
import com.shishkin.auctionapp.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductToEntityMapperImpl implements ProductToEntityMapper {
    private CategoryService categoryService;

    @Override
    public ProductEntity productToProductEntity(Product product) {
        if (product == null) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setId(product.getId());
        productEntity.setTitle(product.getTitle());
        productEntity.setDescription(product.getDescription());
        productEntity.setPrice(product.getPrice());

        productEntity.setImage(product.getImage().getOriginalFilename());
        productEntity.setCategoryId(categoryService.findByTitle(product.getCategoryTitle()).getId());
        return productEntity;
    }

    @Override
    public Product productEntityToProduct(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }

        Product product = new Product();

        product.setId(productEntity.getId());
        product.setTitle(productEntity.getTitle());
        product.setDescription(productEntity.getDescription());
        product.setPrice(productEntity.getPrice());

        product.setImagePath(productEntity.getImagePath());
        product.setCategoryTitle(categoryService.findById(productEntity.getCategoryId()).getTitle());

        return product;
    }
}
