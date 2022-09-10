package com.shishkin.auctionapp.repository.jpa;

import com.shishkin.auctionapp.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
}
