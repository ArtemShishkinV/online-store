package com.shishkin.auctionapp.repository.implementation;

import com.shishkin.auctionapp.entity.ProductEntity;
import com.shishkin.auctionapp.repository.ProductRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private JdbcTemplate jdbcTemplate;

    @Override
    public Iterable<ProductEntity> findAll() {
        return jdbcTemplate.query();
    }

    @Override
    public Optional<ProductEntity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public <S extends ProductEntity> S save(S entity) {
        return null;
    }
}
