package com.shishkin.auctionapp.mapper.entity;

import com.shishkin.auctionapp.entity.CategoryEntity;
import com.shishkin.auctionapp.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryToEntityMapperImpl implements CategoryToEntityMapper{
    @Override
    public CategoryEntity categoryToCategoryEntity(Category category) {
        if(category == null) {
            return null;
        }
        return new CategoryEntity(category.getId(), category.getTitle());
    }

    @Override
    public Category categoryEntityToCategory(CategoryEntity categoryEntity) {
        if(categoryEntity == null) {
            return null;
        }
        return new Category(categoryEntity.getId(), categoryEntity.getTitle());
    }
}
