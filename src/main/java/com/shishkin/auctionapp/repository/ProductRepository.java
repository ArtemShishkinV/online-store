package com.shishkin.auctionapp.repository;

import com.shishkin.auctionapp.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    Iterable<ProductEntity> findByCategoryId(Long id);
}
