package com.shishkin.auctionapp.repository;

import com.shishkin.auctionapp.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
}
