package com.shishkin.auctionapp.repository;

import com.shishkin.auctionapp.entity.CartEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "carts", path = "carts")
public interface CartRepository extends CrudRepository<CartEntity, Long> {
    Iterable<CartEntity> findAllByUserId(Long id);
}
