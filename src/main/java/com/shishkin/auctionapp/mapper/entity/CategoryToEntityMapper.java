package com.shishkin.auctionapp.mapper.entity;

import com.shishkin.auctionapp.entity.CategoryEntity;
import com.shishkin.auctionapp.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface CategoryToEntityMapper {
    @Mapping(target = "id", ignore = true)
    CategoryEntity categoryToCategoryEntity(Category category);
    Category categoryEntityToCategory(CategoryEntity categoryEntity);
}
