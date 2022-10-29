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
    private static final String FIND_BY_TITLE = "SELECT * FROM category WHERE category.title = ?";
    private static final String FIND_BY_ID = "SELECT * FROM category WHERE category.id = ?";
    private static final String INSERT = "INSERT INTO category(title) VALUES(?)";

    private JdbcTemplate jdbcTemplate;
    private CategoryRowMapper mapper;

    @Override
    public CategoryEntity save(CategoryEntity entity) {
        return jdbcTemplate.queryForObject(INSERT, mapper, entity.getTitle());
    }

    @Override
    public Optional<CategoryEntity> findByTitle(String title) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_TITLE, mapper, title));
    }

    @Override
    public Iterable<CategoryEntity> findAll() {
        return jdbcTemplate.query(FIND_ALL, mapper);
    }

    @Override
    public Optional<CategoryEntity> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, mapper, id));
    }


}
