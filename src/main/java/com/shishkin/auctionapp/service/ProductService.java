package com.shishkin.auctionapp.service;

import com.shishkin.auctionapp.entity.ProductEntity;
import com.shishkin.auctionapp.exception.ProductNotFoundException;
import com.shishkin.auctionapp.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(Long id) throws ProductNotFoundException;

    ProductEntity add(Product product);

    List<ProductEntity> saveAll(List<ProductEntity> products);
}
