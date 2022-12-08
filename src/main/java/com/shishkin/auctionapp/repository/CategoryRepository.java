package com.shishkin.auctionapp.repository;

import com.shishkin.auctionapp.entity.CategoryEntity;

import java.sql.SQLException;
import java.util.Optional;

public interface CategoryRepository extends EntityRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByTitle(String title) throws SQLException;
}
