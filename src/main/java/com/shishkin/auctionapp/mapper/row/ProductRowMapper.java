package com.shishkin.auctionapp.mapper.row;

import com.shishkin.auctionapp.entity.CategoryEntity;
import com.shishkin.auctionapp.entity.ProductEntity;
import com.shishkin.auctionapp.exception.CategoryNotFoundException;
import com.shishkin.auctionapp.repository.CategoryRepository;
import com.shishkin.auctionapp.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<ProductEntity> {
    @Override
    public ProductEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductEntity product = new ProductEntity();
        product.setId(rs.getLong("id"));
        product.setTitle(rs.getString("title"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getLong("price"));
        product.setImage(rs.getString("image"));
        product.setCategory(rs.getLong("category_id"));
        return product;
    }

}
