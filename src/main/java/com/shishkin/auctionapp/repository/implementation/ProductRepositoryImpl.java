package com.shishkin.auctionapp.repository.implementation;

import com.shishkin.auctionapp.entity.ProductEntity;
import com.shishkin.auctionapp.mapper.row.ProductRowMapper;
import com.shishkin.auctionapp.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private static final String FIND_ALL = "SELECT * FROM product";
    private static final String FIND_BY_ID = "SELECT * FROM product WHERE product.id = (?)";
    private static final String INSERT = "INSERT INTO product(title, description, price, category_id) " +
            "VALUES (?, ?, ?, ?) RETURNING ID";

    private static final String FIND_BY_CATEGORY_ID = "SELECT * FROM product WHERE product.category_id = (?)";
    public static final String FUNC_DELETE_BY_ID = "delete_product";
    public static final String FUNC_FIND_PRODUCTS_BY_CATEGORY_ID = "SELECT * from find_products_by_category_id(?)";

    private JdbcTemplate jdbcTemplate;
    private ProductRowMapper mapper;

    @Override
    public Iterable<ProductEntity> saveAll(List<ProductEntity> products) {
        jdbcTemplate.batchUpdate(INSERT, products, 50,
                (PreparedStatement ps, ProductEntity entity) -> {
                    ps.setString(1, entity.getTitle());
                    ps.setString(2, entity.getDescription());
                    ps.setLong(3, entity.getPrice());
                    ps.setLong(4, entity.getCategoryId());
                });
        return products;
    }

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
                entity.getTitle(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getCategoryId()));
        return entity;
    }

    @Override
    public Iterable<ProductEntity> findByCategory(Long categoryId) {
//        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withFunctionName(FUNC_FIND_PRODUCTS_BY_CATEGORY_ID);
//        SqlParameterSource in = new MapSqlParameterSource().addValue("_id", categoryId);
//
//        Map<String, Object> out = jdbcCall.execute(in);
//
//        return (List<ProductEntity>) out.get("");

        return jdbcTemplate.query(FUNC_FIND_PRODUCTS_BY_CATEGORY_ID, mapper, categoryId.intValue());
    }

    @Override
    public void deleteById(Long id) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(Objects.requireNonNull(jdbcTemplate.getDataSource()))
                .withFunctionName(FUNC_DELETE_BY_ID);
        SqlParameterSource in = new MapSqlParameterSource().addValue("prod_id", id);
        jdbcCall.execute(in);
    }
}
