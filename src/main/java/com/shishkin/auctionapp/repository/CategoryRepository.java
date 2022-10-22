package com.shishkin.auctionapp.repository;

import com.shishkin.auctionapp.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByTitle(String title);
}
