package com.shishkin.auctionapp.repository.implementation;

import com.shishkin.auctionapp.entity.CategoryEntity;
import com.shishkin.auctionapp.mapper.row.CategoryRowMapper;
import com.shishkin.auctionapp.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
    private static final String FIND_ALL = "SELECT * FROM category";
    private static final String FIND_BY_TITLE = "SELECT * FROM category WHERE category.title = ?";
    private static final String FIND_BY_ID = "SELECT * FROM category WHERE category.id = ?";
    private static final String INSERT = "INSERT INTO category(title) VALUES(?) RETURNING ID";

    private JdbcTemplate jdbcTemplate;
    private CategoryRowMapper mapper;

    @Override
    public CategoryEntity save(CategoryEntity entity) {
        entity.setId(jdbcTemplate.queryForObject(INSERT, Long.class, entity.getTitle()));
        return entity;
    }

    @Override
    public Optional<CategoryEntity> findByTitle(String title) throws SQLException {
        CategoryEntity category;
        try {
            category = jdbcTemplate.queryForObject(FIND_BY_TITLE, mapper, title);
        } catch (EmptyResultDataAccessException e) {
            throw new SQLException(e);
        }
        return Optional.ofNullable(category);
    }

    @Override
    public Iterable<CategoryEntity> findAll() {
        return jdbcTemplate.query(FIND_ALL, mapper);
    }

    @Override
    public Optional<CategoryEntity> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, mapper, id));
    }

    @Override
    public Iterable<CategoryEntity> saveAll(List<CategoryEntity> list) {
        return null;
    }


}
