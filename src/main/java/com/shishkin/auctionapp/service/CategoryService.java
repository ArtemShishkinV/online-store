package com.shishkin.auctionapp.service;

import com.shishkin.auctionapp.exception.CategoryAlreadyExistException;
import com.shishkin.auctionapp.exception.CategoryNotFoundException;
import com.shishkin.auctionapp.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findById(Long id) throws CategoryNotFoundException;

    Category findByTitle(String title) throws CategoryNotFoundException;

    void add(Category category) throws CategoryAlreadyExistException;
}
