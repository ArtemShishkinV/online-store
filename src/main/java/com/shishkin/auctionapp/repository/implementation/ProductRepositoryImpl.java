package com.shishkin.auctionapp.repository.implementation;

import com.shishkin.auctionapp.entity.ProductEntity;
import com.shishkin.auctionapp.mapper.row.ProductRowMapper;
import com.shishkin.auctionapp.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private static final String FIND_ALL = "SELECT * FROM product";
    private static final String FIND_BY_ID = "SELECT * FROM product WHERE product.id = (?)";
    private static final String INSERT = "INSERT INTO product(description, image, price, title, category_id) " +
            "VALUES (?, ?, ?, ?, ?) RETURNING ID";

    @Override
    public Iterable<ProductEntity> saveAll(List<ProductEntity> list) {
        return null;
    }

    private JdbcTemplate jdbcTemplate;
    private ProductRowMapper mapper;

    @Override
    public Iterable<ProductEntity> findAll() {
        return jdbcTemplate.query(FIND_ALL, mapper);
    }

    @Override
    public Optional<ProductEntity> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, mapper, id));
    }

    @Override
    public ProductEntity save(ProductEntity entity) {
        entity.setId(jdbcTemplate.queryForObject(INSERT, Long.class,
                entity.getDescription(),
                entity.getImage(),
                entity.getPrice(),
                entity.getTitle(),
                entity.getCategoryId()));
        return entity;
    }
}
