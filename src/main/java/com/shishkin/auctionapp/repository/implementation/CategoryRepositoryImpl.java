package com.shishkin.auctionapp.repository.implementation;

import com.shishkin.auctionapp.entity.CategoryEntity;
import com.shishkin.auctionapp.mapper.row.CategoryRowMapper;
import com.shishkin.auctionapp.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
    private static final String FIND_ALL = "SELECT * FROM category";

    private JdbcTemplate jdbcTemplate;

    @Override
    public <S extends CategoryEntity> S save(S entity) {
        return null;
    }

    @Override
    public Optional<CategoryEntity> findByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public Iterable<CategoryEntity> findAll() {
        return jdbcTemplate.query(FIND_ALL, new CategoryRowMapper());
    }

    @Override
    public Optional<CategoryEntity> findById(Long aLong) {
        return Optional.empty();
    }


}
