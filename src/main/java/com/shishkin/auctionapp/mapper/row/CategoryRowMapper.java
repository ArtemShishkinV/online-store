package com.shishkin.auctionapp.mapper.row;

import com.shishkin.auctionapp.entity.CategoryEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<CategoryEntity> {
    @Override
    public CategoryEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        CategoryEntity category = new CategoryEntity();
        category.setId(rs.getLong("id"));
        category.setTitle(rs.getString("title"));
        return category;
    }
}
