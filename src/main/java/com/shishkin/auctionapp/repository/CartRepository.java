package com.shishkin.auctionapp.repository;

import com.shishkin.auctionapp.entity.CartEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<CartEntity, Long> {
    Iterable<CartEntity> findAllByUserId(Long id);
}
