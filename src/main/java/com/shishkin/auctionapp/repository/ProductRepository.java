package com.shishkin.auctionapp.repository;

import com.shishkin.auctionapp.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    Iterable<ProductEntity> findByCategoryId(Long id);
}
