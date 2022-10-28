package com.shishkin.auctionapp.repository;

import com.shishkin.auctionapp.entity.CategoryEntity;

import java.util.Optional;

public interface CategoryRepository extends EntityRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByTitle(String title);
}
