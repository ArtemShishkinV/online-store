package com.shishkin.auctionapp.mapper.entity;

import com.shishkin.auctionapp.entity.ProductEntity;
import com.shishkin.auctionapp.model.Product;

public interface ProductToEntityMapper {
    ProductEntity productToProductEntity(Product product);
    Product productEntityToProduct(ProductEntity product);
}
