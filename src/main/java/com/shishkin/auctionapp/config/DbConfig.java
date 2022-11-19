package com.shishkin.auctionapp.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DbConfig {
    @Bean
    public DataSource dataSource() {
        DataSourceBuilder<HikariDataSource> dataSourceBuilder = DataSourceBuilder.create().type(HikariDataSource.class);
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/AuctionDB");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("12345");
        return dataSourceBuilder.build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
