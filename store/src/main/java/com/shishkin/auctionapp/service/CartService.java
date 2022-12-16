package com.shishkin.auctionapp.service;

import com.shishkin.auctionapp.entity.CartEntity;
import com.shishkin.auctionapp.model.Product;

import java.util.List;

public interface CartService {
    List<Product> findAll();

    CartEntity add(Product product);
}
