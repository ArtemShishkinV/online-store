package com.shishkin.auctionapp.repository;

import com.shishkin.auctionapp.entity.ProductEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends EntityRepository<ProductEntity, Long> {
}
