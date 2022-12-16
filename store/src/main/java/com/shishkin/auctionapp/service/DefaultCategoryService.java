package com.shishkin.auctionapp.service;

import com.shishkin.auctionapp.exception.CategoryAlreadyExistException;
import com.shishkin.auctionapp.exception.CategoryNotFoundException;
import com.shishkin.auctionapp.mapper.entity.CategoryToEntityMapper;
import com.shishkin.auctionapp.model.Category;
import com.shishkin.auctionapp.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class DefaultCategoryService implements CategoryService {


    private final CategoryRepository categoryRepository;
    private final CategoryToEntityMapper mapper;

    @Override
    public List<Category> findAll() {
        return StreamSupport.stream(categoryRepository.findAll().spliterator(), false)
                .map(mapper::categoryEntityToCategory).toList();
    }

    @Override
    public Category findById(Long id) {
        return mapper.categoryEntityToCategory(categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(HttpStatus.NOT_FOUND, "Category not found: id = " + id)));
    }

    @Override
    public Category findByTitle(String title) throws CategoryNotFoundException {
        return mapper.categoryEntityToCategory(categoryRepository.findByTitle(title)
                .orElseThrow(() -> new CategoryNotFoundException(HttpStatus.NOT_FOUND, "Category not found: title = " + title)));
    }

    @Override
    public void add(Category category) throws CategoryAlreadyExistException {
        try {
            categoryRepository.save(mapper.categoryToCategoryEntity(category));
        } catch (RuntimeException e) {
            throw new CategoryAlreadyExistException(HttpStatus.BAD_REQUEST,
                    String.format("category with title {%s} already exist", category.getTitle()), e);
        }
    }

    @Override
    public Category update(Category category) {
        Category srcCategory = this.findById(category.getId());
        if (category.getTitle() != null) {
            srcCategory.setTitle(category.getTitle());
        }
        return mapper.categoryEntityToCategory(categoryRepository.save(mapper.categoryToCategoryEntity(srcCategory)));
    }
}
