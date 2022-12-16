package com.shishkin.auctionapp.mapper.entity;

import com.shishkin.auctionapp.entity.CategoryEntity;
import com.shishkin.auctionapp.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface CategoryToEntityMapper {
    CategoryEntity categoryToCategoryEntity(Category category);
    Category categoryEntityToCategory(CategoryEntity categoryEntity);
}
