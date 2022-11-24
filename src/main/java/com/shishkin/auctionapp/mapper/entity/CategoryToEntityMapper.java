package com.shishkin.auctionapp.mapper.entity;

import com.shishkin.auctionapp.entity.CategoryEntity;
import com.shishkin.auctionapp.model.Category;

public interface CategoryToEntityMapper {
    CategoryEntity categoryToCategoryEntity(Category category);
    Category categoryEntityToCategory(CategoryEntity categoryEntity);
}
